package database;

import model.Staff;

public class StaffDAO {
    /** 
     * Finds a Staff from the database using the id
     * @param staffId The Staff id of the Staff  
     * @return Staff The corresponding Staff object, NULL if not found
     */
    public static Staff getUserbyId(String staffId) {
        return Database.STAFFS.get(staffId);
    }
    
    /** 
     * Adds a Staff to the database
     * @param staff The Staff Object to save
     */
    public static void createUser(Staff staff) {
        Database.STAFFS.put(staff.getUserId(), staff);
    }

    /** 
     * Removes a Staff from the database using the id
     * @param staffId The Staff id of the Staff
     */
    public static void deleteUser(String staffId) {
        Database.STAFFS.remove(staffId);
    }
}
