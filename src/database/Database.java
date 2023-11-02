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
    /**
     * The folder where the database files are saved.
     */
    private static final String folder = "data";
    /**
     * The file extension of the database files.
     */
    private static final String fileExtension = ".dat";

    /**
     * The hashmap of User id and User.
     */
    public static HashMap<String, User> USERS = new HashMap<String, User>();
    /**
     * The hashmap of Student id and Student.
     */
    public static HashMap<String, Student> STUDENTS = new HashMap<String, Student>();
    /**
     * The hashmap of Staff id and Staff.
     */
    public static HashMap<String, Staff> STAFFS = new HashMap<String, Staff>();
    /**
     * The hashmap of Camp id and Camp.
     */
    public static HashMap<Integer, Camp> CAMPS = new HashMap<Integer, Camp>();
    /**
     * The hashmap of Enquiry id and Enquiry.
     */
    public static HashMap<Integer, Enquiry> ENQUIRYS = new HashMap<Integer, Enquiry>();
    /**
     * The hashmap of Suggestion id and Suggestion.
     */
    public static HashMap<Integer, Suggestion> SUGGESTIONS = new HashMap<Integer, Suggestion>();
    
    /**
     * Initialises the database from the .dat files.
     */
    public Database() {
        readAllFromDatabase();
    }

    /**
     * Sets the database variables from the .dat files,
     * for all FileNames in enum FileName.
     */
    public static void readAllFromDatabase() {
        for (FileName fileName : FileName.values()) {
            readFromDatabase(fileName);
        }
        /*
        readFromDatabase(FileName.USERS);
        readFromDatabase(FileName.STUDENTS);
        readFromDatabase(FileName.STAFFS);
        readFromDatabase(FileName.CAMPS);
        readFromDatabase(FileName.ENQUIRYS);
        readFromDatabase(FileName.SUGGESTIONS);
        */
    }

    /** 
     * @param fileName The appropriate filename from enum FileName 
     * @return boolean Whether the database variable is updated
     */
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
                case ENQUIRYS:
                    ENQUIRYS = (HashMap<Integer, Enquiry>) object;
                    break;
                case SUGGESTIONS:
                    SUGGESTIONS = (HashMap<Integer, Suggestion>) object;
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
                case ENQUIRYS:
                    ENQUIRYS = new HashMap<Integer, Enquiry>();
                    break;
                case SUGGESTIONS:
                    SUGGESTIONS = new HashMap<Integer, Suggestion>();
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

    /** 
     * Saves the database variables to .dat files
     */
    public static void saveAllToDatabase() {
        saveToDatabase(FileName.USERS, Database.USERS);
        saveToDatabase(FileName.STUDENTS, Database.STUDENTS);
        saveToDatabase(FileName.STAFFS, Database.STAFFS);
        saveToDatabase(FileName.CAMPS, Database.CAMPS);
        saveToDatabase(FileName.ENQUIRYS, Database.ENQUIRYS);
        saveToDatabase(FileName.SUGGESTIONS, Database.SUGGESTIONS);
    }

    
    /** 
     * @param fileName The appropriate file from enum FileName  
     * @param whattosave The corresponding database variable
     * @return boolean Whether the database variable is saved to the file
     */
    public static boolean saveToDatabase(FileName fileName, Object whattosave) {
        String filePath = getFilePath(fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(whattosave);
            /*
            switch(fileName) {
                case USERS:
                    objectOutputStream.writeObject(USERS);
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

    
    /** 
     * @param fileName The file from enum FileName  
     * @return String Returns the filepath for the database file
     */
    public static String getFilePath(FileName fileName) {
        return "./src/database/" + folder + "/" + fileName.getFileNameStr() + fileExtension;
    }
}
