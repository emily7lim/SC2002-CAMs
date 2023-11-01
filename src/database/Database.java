package database;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import model.*;

public class Database {
    private static final String folder = "data";
    private static final String fileExtension = ".dat";

    public static HashMap<String, User> USERS = new HashMap<String, User>();
    public static HashMap<String, Student> STUDENTS = new HashMap<String, Student>();
    public static HashMap<String, Staff> STAFFS = new HashMap<String, Staff>();
    public static HashMap<Integer, Camp> CAMPS = new HashMap<Integer, Camp>();

    public Database() {
        readAllFromDatabase();
    }

    public static void readAllFromDatabase() {
        readFromDatabase(FileName.USERS);
        readFromDatabase(FileName.STUDENTS);
        readFromDatabase(FileName.STAFFS);
        readFromDatabase(FileName.CAMPS);
    }

    public static boolean readFromDatabase(FileName fileName) {
        String filePath = getFilePath(fileName);

        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            if (!(object instanceof HashMap)) {
                objectInputStream.close();
                fileInputStream.close();

                return false;
            }
            
            switch(fileName) {
                case USERS:
                    USERS = (HashMap<String, User>) object;
                    break;
                case STUDENTS:
                    STUDENTS = (HashMap<String, Student>) object;
                    break;
                case STAFFS:
                    STAFFS = (HashMap<String, Staff>) object;
                    break;
                case CAMPS:
                    CAMPS = (HashMap<Integer, Camp>) object;
                    break;
                default:
                    break;
            }

            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException err) {
            System.out.println("Warning: " + err.getMessage());
            switch(fileName) {
                case USERS:
                    USERS = new HashMap<String, User>();
                    break;
                case STUDENTS:
                    STUDENTS = new HashMap<String, Student>();
                    break;
                case STAFFS:
                    STAFFS = new HashMap<String, Staff>();
                    break;
                case CAMPS:
                    CAMPS = new HashMap<Integer, Camp>();
                    break;
                default:
                    break;
            }
        } catch (IOException err) {
            err.printStackTrace();
            return false;
        } catch (ClassNotFoundException err) {
            err.printStackTrace();
            return false;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }

        return true;
    }

    public static void saveAllToDatabase() {
        saveToDatabase(FileName.USERS, Database.USERS);
        saveToDatabase(FileName.STUDENTS, Database.STUDENTS);
        saveToDatabase(FileName.STAFFS, Database.STAFFS);
        saveToDatabase(FileName.CAMPS, Database.CAMPS);
    }

    public static boolean saveToDatabase(FileName fileName, Object whattosave) {
        String filePath = getFilePath(fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(whattosave);
            /*
            switch(fileName) {
                case USERS:
                    break;
                case STUDENTS:
                    objectOutputStream.writeObject(STUDENTS);
                    break;
                case STAFFS:
                    objectOutputStream.writeObject(STAFFS);
                    break;
                case CAMPS:
                    objectOutputStream.writeObject(CAMPS);
                    break;
                default:
                    break;
            }*/

            objectOutputStream.close();
            fileOutputStream.close();

            return true;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
    }

    public static String getFilePath(FileName fileName) {
        return "./src/database/" + folder + "/" + fileName.getFileNameStr() + fileExtension;
    }
}
