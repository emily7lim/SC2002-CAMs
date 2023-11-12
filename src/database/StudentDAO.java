package database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Student;
import model.User;
import model.enums.Role;

public class StudentDAO {
    public static List<Role> studentRoles = Arrays.asList(Role.STUDENT, Role.COMMITTEE);

    /** 
     * Adds a student to the database
     * @param student The Student Object to save
     */
    public static void createStudent(Student student) {
        Database.USERS.put(student.getUserId(), student);
    }

    /** 
     * Retrieve all Students from the database
     * @return ArrayList<Student> The list of all Students
     */
    public static ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        for (User user : Database.USERS.values()) {
            if (studentRoles.contains(user.getRole())) students.add((Student) user);
        }

        return students;
    }

    /** 
     * Finds a student from the database using the ID
     * @param userId The User ID of the User  
     * @return Student The corresponding Student object, NULL if not found
     */
    public static Student getStudentbyId(String userId) {
        User user = Database.USERS.get(userId);

        if (user != null && studentRoles.contains(user.getRole())) return (Student) user;
        else return null;
    }

    /** 
     * Check if a User exists in the database using the ID
     * @param userId The User ID of the User
     * @return boolean Whether the User exists
     */
    public static boolean checkStudent(String userId) {
        User user = Database.USERS.get(userId);

        if (user != null && studentRoles.contains(user.getRole())) return true;
        else return false;
    }

    /**
     * Update the points of a Student in the database using the ID 
     * @param userId The User ID of the Student
     */
    public static void updateStudentPoints(String userId) {
        ((Student) Database.USERS.get(userId)).addPoint();;
    }

    /**
     * Update the Camp IDs of a Student in the database using the ID 
     * @param userId The User ID of the Student
     * @param campId The Camp ID of the Camp the Student registered for
     */
    public static void updateStudentCamps(String userId, String campId) {
        ((Student) Database.USERS.get(userId)).addCamp(campId);
    }

    /**
     * Updates the Role of a Student in the database using the ID
     * @param userId The User ID of the Student
     * @param role The new Role of the Student
     */
    public static boolean updateStudentRole(String userId, Role role) {
        if (studentRoles.contains(role)) {
            Database.USERS.get(userId).setRole(role);
            return true;
        } else return false;
    }
}
