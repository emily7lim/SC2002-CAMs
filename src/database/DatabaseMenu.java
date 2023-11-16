package database;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

import model.*;
import model.enums.Faculty;
// just for us to use to set the .dat
public class DatabaseMenu {
    private static HashMap<String, Camp> dbTable;
    public static void main(String[] args) {
        new Database();
        dbTable = Database.CAMPS;
        
        dbTable.forEach((key, camp) -> {
            // Modify the value (e.g., double it)
            if(camp.getWithdrawnParticipantIds() == null) camp.setWithdrawnParticipantIds(new ArrayList<>());
            if(camp.getParticipantIds() == null) camp.setParticipantIds(new ArrayList<>());
            if(camp.getCommitteeIds() == null) camp.setCommitteeIds(new ArrayList<>());
            if(camp.getEnquiryIds() == null) camp.setEnquiryIds(new ArrayList<>());
            if(camp.getSuggestionIds() == null) camp.setSuggestionIds(new ArrayList<>());

            dbTable.put(key, camp);
        });
        
        Database.saveAllToDatabase();
    }
    public static void printdb(){
        DatabaseMenu.dbTable.forEach((userId, user) -> {
            System.out.println("UserID: " + userId + ", Name: " + user.getName());
        });
    }
    private static String getUID(String email){
        int end = email.indexOf("@");
        if(end == -1){
            return email;
        } else {
            return email.substring(0, end);
        }
    }
    private void updateCamp(){

    }
    /*
    private void something(){
        Scanner sc = new Scanner(System.in);

        String[] choices = new String[3];
        String choice = "";
        int count = 0;
        while(!choice.equals("-exit")){
            System.out.println("choice: ");
            choice = sc.next();
            if(choice.equals("show")){
                DatabaseMenu.printdb();
                continue;
            } else if (choice.equals("clear")){
                Database.USERS.clear();
                System.out.println("db table clear");
                continue;
            }
            
            choices[count] = choice;
            if(count++ >= 2){
                count = 0;

                Faculty fal = Faculty.valueOf(choices[2]);
                String uid = getUID(choices[1]);
                System.out.println("user ID: "+uid);
                Staff staff = new Staff(uid, choices[0], "password", fal);
                
                dbTable.put(staff.getUserId(), staff);
            }
        }
    }
    */
}
