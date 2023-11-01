package controller;

import model.Camp;
import model.Staff;
import model.enums.Faculty;
import model.enums.Role;

import java.util.ArrayList;
import java.util.List;

public class StaffController {
    private List<Staff> staffMembers;

    public StaffController() {
        staffMembers = new ArrayList<>();
    }

    // Create a new staff profile and add it to the list
    public Staff createStaff(String userId, String name, String password, Faculty faculty) {
        Staff staff = new Staff(userId, name, password, faculty, Role.STAFF);
        staffMembers.add(staff);
        return staff;
    }

    // Get a list of all staff profiles
    public List<Staff> getAllStaffMembers() {
        return staffMembers;
    }

    // Find a staff member by their user ID
    public Staff findStaffByUserId(String userId) {
        for (Staff staff : staffMembers) {
            if (staff.getUserId().equals(userId)) {
                return staff;
            }
        }
        return null; //No staff by that ID
    }
}
