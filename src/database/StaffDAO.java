package database;

import java.util.ArrayList;

import model.Staff;
import model.User;
import model.enums.Role;

public class StaffDAO {
    public static Role staffRole = Role.STAFF;
    
    /** 
     * Adds a staff to the database
     * @param staff The Staff Object to save
     */
    public static void createStaff(Staff staff) {
        Database.USERS.put(staff.getUserId(), staff);
    }

    /** 
     * Retrieve all Staffs from the database
     * @return ArrayList<Staff> The list of all Staffs
     */
    public static ArrayList<Staff> getAllStaffs() {
        ArrayList<Staff> staffs = new ArrayList<>();

        for (User user : Database.USERS.values()) {
            if (user.getRole() == staffRole) staffs.add((Staff) user);
        }

        return staffs;
    }

    /** 
     * Finds a Staff from the database using the ID
     * @param userId The User ID of the Staff
     * @return Staff The corresponding Staff object, NULL if not found
     */
    public static Staff getStaffbyId(String userId) {
        User user = Database.USERS.get(userId);

        if (user != null && user.getRole() == staffRole) return (Staff) user;
        else return null;
    }

    /** 
     * Check if a Staff exists in the database using the ID
     * @param userId The User ID of the Staff
     * @return boolean Whether the Staff exists
     */
    public static boolean checkStaff(String userId) {
        User user = Database.USERS.get(userId);

        if (user != null && user.getRole() == staffRole) return true;
        else return false;
    }

    /**
     * Update the Camp IDs of a Staff in the database using the ID 
     * @param userId The User ID of the Staff
     * @param campId The Camp ID of the Camp created by the Staff
     */
    public static void updateStaffCamps(String userId, String campId) {
        ((Staff) Database.USERS.get(userId)).addCamp(campId);
    }
}
