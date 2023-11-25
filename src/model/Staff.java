package model;

import java.util.ArrayList;

import model.enums.Faculty;
import model.enums.Role;

/**
 * User in the System with Staff Role
 * 
 * @author Owen, Chloie
 * @version 2.2.2
 * @since 2023-10-26
 */
public class Staff extends User {
    protected static final long serialVersionUID = 5L; // Serializable Unique Identifier
    private ArrayList<String> createdCampIds; // The Created Camp IDs of the Staff

    /**
     * Constructs and initializes a Staff object with User ID, Name, Password and
     * Faculty
     * 
     * @param userId   The User ID of the Staff
     * @param name     The Name of the Staff
     * @param password The Password of the Staff
     * @param faculty  The Faculty of the Staff
     */
    public Staff(String userId, String name, String password, Faculty faculty) {
        super(userId, name, password, faculty, Role.STAFF);
        createdCampIds = new ArrayList<>();
    }

    /**
     * Gets the Camp IDs of the Camps the Staff created
     * 
     * @return ArrayList<String> The Camp IDs of the Camps the Staff created
     */
    public ArrayList<String> getCreatedCampIds() {
        return createdCampIds;
    }

    /**
     * Sets the Camp IDs of the Camps the Staff created
     * 
     * @param createdCampIds The Camp IDs of the Camps the Staff created
     */
    public void setCreatedCampIds(ArrayList<String> createdCampIds) {
        this.createdCampIds = createdCampIds;
    }

    /**
     * Adds a Camp ID to the list of Camp IDs of Camps the Staff created
     * 
     * @param campId The Camp ID of the Camp the Staff created
     */
    public void addCamp(String campId) {
        this.createdCampIds.add(campId);
    }
}
