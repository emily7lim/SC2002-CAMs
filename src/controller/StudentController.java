package controller;

import model.Student;
import model.enums.Faculty;

import java.util.ArrayList;

import database.StudentDAO;
import database.UserDAO;

public class StudentController {
    /**
     * Creates a new Student and adds to database
     * @param userId The User ID of the new Student
     * @param name The name of the new Student
     * @param password The password of the new Student
     * @param faculty The facult of the new Student
     */
    public void createStudent(String userId, String name, String password, Faculty faculty) {
        Student student = new Student(userId, name, password, faculty);
        StudentDAO.createStudent(student);
    }

    /**
     * Retrieves a list of all the Students from the database
     * @return ArrayList<Student> The list of all the Students
     */
    public ArrayList<Student> getAllStudents() {
        return StudentDAO.getAllStudents();
    }

    /**
     * Finds a Student from the Database by the User ID
     * @param userId The User ID of the Student
     * @return Student The corresponding Student object, NULL if not found
     */
    public Student getStudentByUserId(String userId) {
        return StudentDAO.getStudentbyId(userId);
    }

    /**
     * Adds a point for a Student
     * @param userId The User ID of the Student
     * @return boolean Whether the Student points was successfully updated
     */
    public boolean addPoint(String userId) {
        if (!checkStudentExists(userId)) return false;

        StudentDAO.updateStudentPoints(userId);
        return true;
    }

    /**
     * Adds a Camp ID of a Camp a Student registers for
     * @param userId The User ID of the Student
     * @param campId The Camp ID of the Camp the Student registered for
     * @return boolean Whether the Student Camp IDs was successfully updated
     */
    public boolean addCamp(String userId, String campId) {
        if (!checkStudentExists(userId)) return false;

        StudentDAO.updateStudentCamps(userId, campId);
        return true;
    }

    /**
     * Deletes a Student from the database
     * @param userId The User ID of the Student
     * @return boolean Whether the Student was successfully deleted
     */
    public boolean deleteStudent(String userId) {
        if (!checkStudentExists(userId)) return false;

        UserDAO.deleteUser(userId);
        return true;
    }

    /**
     * Check if the Student with User ID exists in the Database
     * @param userId The User ID of the Student
     * @return boolean Whether the Student exists in the Database
     */
    public boolean checkStudentExists(String userId) {
        return StudentDAO.checkStudent(userId);
    }

    /**
     * Initializes the initial dummy data of Students
     */
    public void initializeStudentData() {
        String password = "password";
        createStudent("YCHERN", "CHERN", password, Faculty.SCSE);
        createStudent("KOH1", "KOH", password, Faculty.ADM);
        createStudent("BR015", "BRANDON", password, Faculty.EEE);
        createStudent("CT113", "CALVIN", password, Faculty.SCSE);
        createStudent("YCN019", "CHAN", password, Faculty.NBS);
        createStudent("DL007", "DENISE", password, Faculty.SCSE);
        createStudent("DON84", "DONG", password, Faculty.ADM);
        createStudent("ELI34", "ERNEST", password, Faculty.EEE);
        createStudent("LE51", "LEE", password, Faculty.SCSE);
        createStudent("SL22", "LIU", password, Faculty.NBS);
        createStudent("AKY013", "RAWAL", password, Faculty.SSS);
    }
}
