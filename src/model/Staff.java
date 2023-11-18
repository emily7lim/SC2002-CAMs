package model;

import java.util.ArrayList;

import model.enums.Faculty;
import model.enums.Role;

public class Staff extends User {
    protected static final long serialVersionUID = 5L;
    private ArrayList<String> createdCampIds;

    public Staff(String userId, String name, String password, Faculty faculty) {
        super(userId, name, password, faculty, Role.STAFF);
        createdCampIds = new ArrayList<>();
    }

    public ArrayList<String> getCreatedCampIds() {
        return createdCampIds;
    }

    public void setCreatedCampIds(ArrayList<String> createdCampIds) {
        this.createdCampIds = createdCampIds;
    }

    public void addCamp(String campId) {
        this.createdCampIds.add(campId);
    }
}
