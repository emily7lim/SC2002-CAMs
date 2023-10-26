package model;

import java.util.ArrayList;

public class Staff extends User{
    private ArrayList<Camp> createdCamps = new ArrayList<Camp>();

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
