package model;

import java.util.ArrayList;

import model.enums.Faculty;
import model.enums.Role;

public class Student extends User {
    protected static final long serialVersionUID = 6L;
    private int points;
    private ArrayList<String> campIds;

    public Student(String userId, String name, String password, Faculty faculty) {
        super(userId, name, password, faculty, Role.STUDENT);
        this.points = 0;
        this.campIds = new ArrayList<String>();
    }

    public Student(String userId, String name, String password, Faculty faculty, Role role) {
        super(userId, name, password, faculty, role);
        this.points = 0;
        this.campIds = new ArrayList<String>();
    }

    
    /** 
     * @return int
     */
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<String> getCampIds() {
        return campIds;
    }

    public void setCampIds(ArrayList<String> campIds) {
        this.campIds = campIds;
    }

    public void addPoint() {
        this.points = this.points + 1;
    }

    public void addCamp(String campId) {
        this.campIds.add(campId);
    }
}
