package model;

import java.util.ArrayList;

import model.enums.Faculty;
import model.enums.Role;

/**
 * User in the System with Student Role
 * 
 * @author Chloie
 * @version 2.2.2
 * @since 2023-10-23
 */
public class Student extends User {
    protected static final long serialVersionUID = 6L; // Serializable Unique Identifier
    private int points; // Points of the Student
    private ArrayList<String> campIds; // Camp IDs of the Student

    /**
     * Constructs and initializes a Student object with User Id, Name, Password and
     * Faculty
     * 
     * @param userId   The User ID of the Student
     * @param name     The Name of the Student
     * @param password The Password of the Student
     * @param faculty  The Faculty of the Student
     */
    public Student(String userId, String name, String password, Faculty faculty) {
        super(userId, name, password, faculty, Role.STUDENT);
        this.points = 0;
        this.campIds = new ArrayList<String>();
    }

    /**
     * Constructs and initializes a Student object with User ID, Name, Password,
     * Faculty and Role
     * 
     * @param userId   The User ID of the Student
     * @param name     The Name of the Student
     * @param password The Password of the Student
     * @param faculty  The Faculty of the Student
     * @param role     The Role of the Student
     */
    public Student(String userId, String name, String password, Faculty faculty, Role role) {
        super(userId, name, password, faculty, role);
        this.points = 0;
        this.campIds = new ArrayList<String>();
    }

    /**
     * Gets the number of points a Student has
     * 
     * @return int The number of points a Student has
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the number of points a Student has
     * 
     * @param points The number of points a Student has
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the Camp IDs of Camps the Student is registered for
     * 
     * @return The Camp IDs of Camps the Student is registered for
     */
    public ArrayList<String> getCampIds() {
        return campIds;
    }

    /**
     * Sets the Camp IDs of Camps the Student is registered for
     * 
     * @param campIds The Camp IDs of Camps the Student is registered for
     */
    public void setCampIds(ArrayList<String> campIds) {
        this.campIds = campIds;
    }

    /**
     * Increment the number of points a Student has
     */
    public void addPoint() {
        this.points = this.points + 1;
    }

    /**
     * Adds a Camp ID to the list of Camp IDs of Camps the Student is registered for
     * 
     * @param campId The Camp ID of a Camp the Student registered for
     */
    public void addCamp(String campId) {
        this.campIds.add(campId);
    }
}
