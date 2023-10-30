package model;

import java.util.ArrayList;

import model.enums.Faculty;
import model.enums.Role;

public class Staff extends User{
    private ArrayList<Camp> createdCamps;

    public Staff(String userId, String name, String password, Faculty faculty, Role role) {
        super(userId, name, password, faculty, Role.STAFF);
        createdCamps = new ArrayList<Camp>();
    }

    public ArrayList<Camp> getCreatedCamps() {
        return createdCamps;
    }

    public void setCreatedCamps(ArrayList<Camp> createdCamps) {
        this.createdCamps = createdCamps;
    }

    public Boolean checkCamp(Camp camp){
        int i;
        for(i=0;i<createdCamps.size();i++){
            // TODO
            // need to implement the comparing part of the camp
            if(createdCamps.get(i) == camp){
                return true;
            }
        }
        return false;
    }
}
