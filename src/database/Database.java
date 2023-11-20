package database;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import controller.StaffController;
import controller.StudentController;
import model.*;
import model.enums.Faculty;
import view.ForDate;

public class Database {
    /**
     * The folder where the database files are saved
     */
    private static final String folder = "data";
    /**
     * The file extension of the database files
     */
    private static final String fileExtension = ".dat";

    /**
     * The hashmap of User ID and User
     */
    public static HashMap<String, User> USERS = new HashMap<String, User>();
    /**
     * The hashmap of Camp ID and Camp
     */
    public static HashMap<String, Camp> CAMPS = new HashMap<String, Camp>();
    /**
     * The hashmap of Enquiry ID and Enquiry
     */
    public static HashMap<String, Enquiry> ENQUIRIES = new HashMap<String, Enquiry>();
    /**
     * The hashmap of Suggestion ID and Suggestion
     */
    public static HashMap<String, Suggestion> SUGGESTIONS = new HashMap<String, Suggestion>();

    /**
     * Initialises the database from the .dat files
     */
    public Database() {
        readAllFromDatabase();
    }

    /**
     * Sets the database variables from the .dat files,
     * for all FileNames in enum FileName
     */
    public void readAllFromDatabase() {
        for (FileName fileName : FileName.values()) {
            readFromDatabase(fileName);
        }
    }

    /**
     * @param fileName The appropriate file from enum FileName
     * @return boolean Whether the database variable is retrieved
     */
    @SuppressWarnings("unchecked")
    public boolean readFromDatabase(FileName fileName) {
        String filePath = getFilePath(fileName);

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            if (!(object instanceof HashMap)) {
                objectInputStream.close();
                fileInputStream.close();

                return false;
            }

            switch (fileName) {
                case USERS:
                    USERS = (HashMap<String, User>) object;
                    break;
                case CAMPS:
                    CAMPS = (HashMap<String, Camp>) object;
                    break;
                case ENQUIRIES:
                    ENQUIRIES = (HashMap<String, Enquiry>) object;
                    break;
                case SUGGESTIONS:
                    SUGGESTIONS = (HashMap<String, Suggestion>) object;
                    break;
                default:
                    break;
            }

            objectInputStream.close();
            fileInputStream.close();
        } catch (EOFException err) {
            System.out.println("Warning: " + err.getMessage());
            switch (fileName) {
                case USERS:
                    USERS = new HashMap<String, User>();
                    break;
                case CAMPS:
                    CAMPS = new HashMap<String, Camp>();
                    break;
                case ENQUIRIES:
                    ENQUIRIES = new HashMap<String, Enquiry>();
                    break;
                case SUGGESTIONS:
                    SUGGESTIONS = new HashMap<String, Suggestion>();
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
    public void saveAllToDatabase() {
        for (FileName fileName : FileName.values()) {
            saveToDatabase(fileName);
        }
    }

    /**
     * @param fileName The appropriate file from enum FileName
     * @return boolean Whether the database variable is saved to the file
     */
    public boolean saveToDatabase(FileName fileName) {
        String filePath = getFilePath(fileName);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            switch (fileName) {
                case USERS:
                    objectOutputStream.writeObject(USERS);
                    break;
                case CAMPS:
                    objectOutputStream.writeObject(CAMPS);
                    break;
                case ENQUIRIES:
                    objectOutputStream.writeObject(ENQUIRIES);
                    break;
                case SUGGESTIONS:
                    objectOutputStream.writeObject(SUGGESTIONS);
                    break;
                default:
                    break;
            }

            objectOutputStream.close();
            fileOutputStream.close();

            return true;
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
            return false;
        }
    }

    /**
     * Clears all data from database and .dat files
     */
    public void clearDatabase() {
        for (FileName fileName : FileName.values()) {
            clearFileData(fileName);
        }
    }

    /**
     * Clears data from specific file in database and .dat file
     * 
     * @param fileName The appropriate file from enum FileName
     */
    public void clearFileData(FileName fileName) {
        switch (fileName) {
            case USERS:
                USERS = new HashMap<String, User>();
                break;
            case CAMPS:
                CAMPS = new HashMap<String, Camp>();
                break;
            case ENQUIRIES:
                ENQUIRIES = new HashMap<String, Enquiry>();
                break;
            case SUGGESTIONS:
                SUGGESTIONS = new HashMap<String, Suggestion>();
                break;
            default:
                break;
        }

        saveToDatabase(fileName);
    }

    /**
     * @param fileName The file from enum FileName
     * @return String Returns the filepath for the database file
     */
    public String getFilePath(FileName fileName) {
        return "./src/database/" + folder + "/" + fileName.getFileNameStr() + fileExtension;
    }

    public void initializeUserData() {
        StudentController.initializeStudentData();
        StaffController.initializeStaffData();
    }

    public void initializeCampData() {
        Camp camp = new Camp("Test Camp", ForDate.getDates("12/02/2024"), ForDate.getDates("15/02/2024"),
                ForDate.getDates("25/12/2024"), Faculty.SCSE, "Sentosa", 10, 3, "Testing Camp Description", "HUKUMAR");
        camp.addCommittee("YCHERN");
        Database.CAMPS.put(camp.getCampId(), camp);
        StudentController.addCampCommittee("YCHERN");
        StudentController.addCamp("YCHERN", camp.getCampId());
        StaffController.addCamp("HUKUMAR", camp.getCampId());
    }

    public void initializeDummyData() {
        clearDatabase();

        initializeUserData();
        initializeCampData();

        saveAllToDatabase();
    }

    public boolean checkDataEmpty(FileName fileName) {
        switch (fileName) {
            case USERS:
                return USERS.size() == 0;
            case CAMPS:
                return CAMPS.size() == 0;
            case ENQUIRIES:
                return ENQUIRIES.size() == 0;
            case SUGGESTIONS:
                return SUGGESTIONS.size() == 0;
            default:
                break;
        }

        return false;
    }
}
