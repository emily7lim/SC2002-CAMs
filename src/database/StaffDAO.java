package database;

import model.Staff;

public class StaffDAO {
    public static Staff getUserbyId(String staffId) {
        return Database.STAFFS.get(staffId);
    }
    
    public static void createUser(Staff staff) {
        Database.STAFFS.put(staff.getUserId(), staff);
    }
}
