package controller;

import model.Staff;
import model.enums.Faculty;

import java.util.ArrayList;

import database.StaffDAO;
import database.UserDAO;

public class StaffController {
    /**
     * Creates a new Staff and adds to the Database
     * @param userId The User ID of the new Staff
     * @param name The name of the new Staff
     * @param password The password of the new Staff
     * @param faculty The faculty of the new Staff
     */
    public void createStaff(String userId, String name, String password, Faculty faculty) {
        Staff staff = new Staff(userId, name, password, faculty);
        StaffDAO.createStaff(staff);
    }

    /**
     * Retrieves a list of all the Staffs from the database
     * @return ArrayList<Staff> The list of all the Staffs
     */
    public ArrayList<Staff> getAllStaffs() {
        return StaffDAO.getAllStaffs();
    }

    /**
     * Finds a Staff from the database by the User ID
     * @param userId The User ID of the Staff
     * @return Staff The corresponding Staff object, NULL if not found
     */
    public Staff getStaffByUserId(String userId) {
        return StaffDAO.getStaffbyId(userId);
    }

    /**
     * Adds a Camp ID of a Camp a Staff creates
     * @param userId The User ID of the Staff
     * @param campId The Camp ID of the Camp created by the Staff 
     * @return boolean Whether the Staff Camp IDs was successfully updated
     */
    public boolean addCamp(String userId, String campId) {
        if (!checkStaffExists(userId)) return false;

        StaffDAO.updateStaffCamps(userId, campId);
        return true;
    }

    /**
     * Deletes a Staff from the database
     * @param userId The User ID of the Staff
     * @return boolean Whether the Staff was successfully deleted
     */
    public boolean deleteStaff(String userId) {
        if (!checkStaffExists(userId)) return false;

        UserDAO.deleteUser(userId);
        return true;
    }

    /**
     * Check if the Staff with User ID exists in the database
     * @param userId The User ID of the Staff
     * @return boolean Whether the Staff exists in the database
     */
    public boolean checkStaffExists(String userId) {
        return StaffDAO.checkStaff(userId);
    }
}
