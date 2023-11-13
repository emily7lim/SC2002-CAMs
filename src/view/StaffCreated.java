package view;

import java.util.*;
import java.text.SimpleDateFormat;

import controller.*;
import model.enums.Faculty;

public class StaffCreated {
    public static void main(String[] args){
        String loggedID = "ARVI"; // note:take from login detail

        Scanner sc = new Scanner(System.in);
        Boolean continues = true;

        SimpleDateFormat strdate = new SimpleDateFormat("dd/MM/yyyy");

        while (continues) {
            System.out.println("______________\n");
            System.out.println("Created Camps");
            System.out.println("______________");

            ArrayList<String> getcampid = new ArrayList<>();
            ArrayList<String> getcampname = new ArrayList<>();
            ArrayList<Date> getcampsdate = new ArrayList<>();
            ArrayList<Date> getcampedate = new ArrayList<>();
            ArrayList<Date> getcampdeadline = new ArrayList<>();
            ArrayList<Faculty> getcampgrp = new ArrayList<>();
            ArrayList<String> getcamplocation = new ArrayList<>();
            ArrayList<Integer> getcampslot = new ArrayList<>();
            ArrayList<Integer> getcampcommslot = new ArrayList<>();
            ArrayList<String> getcampdesc = new ArrayList<>();
            ArrayList<String> getcampic = new ArrayList<>();
            ArrayList<Boolean> getcampvisible = new ArrayList<>();

            for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                if (CampController.getAllCamps().get(i).getStaffInCharge() == loggedID) {
                    getcampid.add(CampController.getAllCamps().get(i).getCampId());
                    getcamplocation.add(CampController.getAllCamps().get(i).getLocation());
                    getcampname.add(CampController.getAllCamps().get(i).getName());
                    getcampdesc.add(CampController.getAllCamps().get(i).getDescription());
                    getcampgrp.add(CampController.getAllCamps().get(i).getUserGroup());
                    getcampic.add(CampController.getAllCamps().get(i).getStaffInCharge());
                    getcampslot.add(CampController.getAllCamps().get(i).getTotalSlots());
                    getcampcommslot.add(CampController.getAllCamps().get(i).getCommSlots());
                    getcampsdate.add(CampController.getAllCamps().get(i).getStartDate());
                    getcampedate.add(CampController.getAllCamps().get(i).getEndDate());
                    getcampdeadline.add(CampController.getAllCamps().get(i).getRegistrationCloseDate());
                    getcampvisible.add(CampController.getAllCamps().get(i).isVisible());
                }

            }
            for (int i = 0; i < getcampic.size(); i++) {
                System.out.println("\n*****Details of camp " + i + "*******");
                System.out.println("Camp Name: " + getcampname.get(i) + "\nStart date: " +
                        strdate.format(getcampsdate.get(i)) + "\nEnd date: " + strdate.format(getcampedate.get(i))
                        + "\nRegistration deadline: " + strdate.format(getcampdeadline.get(i)) + "\nUser group: "
                        + getcampgrp.get(i) + "\nLocation: " +
                        getcamplocation.get(i) + "\nTotal slots: " + getcampslot.get(i) + "\nCommittee slots: "
                        + getcampcommslot.get(i) + "\nCamp IC: " + getcampic.get(i) + "\nDescription: " +
                        getcampdesc.get(i) + "\nVisibility: " + getcampvisible.get(i));

            }
            System.out.println("\n1) Create\n2) Edit\n3) Delete\n4) Toggle Visibility\n5) Quit\nSelect your choice:");
            Integer edit = sc.nextInt();
            switch (edit) {
                case 1:

                    Scanner scan = new Scanner(System.in);
                    String name = "", location = "", description = "";
                    Faculty grp = Faculty.ADM;

                    Integer commslot = 0, totalslot = 0;

                    System.out.println("***********You are now creating***********");
                    System.out.println("Enter camp name:");
                    name += scan.nextLine();

                    System.out.println("Enter your start date (dd/mm/yyyy):");
                    Scanner scand = new Scanner(System.in);
                    String sdate = scand.nextLine();
                    while (ForDate.getDates(sdate) == null) {
                        sdate = scand.nextLine();
                    }
                    Date startdate = ForDate.getDates(sdate);

                    System.out.println("Enter your end date (dd/mm/yyyy):");
                    String edate = scand.nextLine();
                    while (ForDate.getDates(edate) == null) {
                        edate = scand.nextLine();
                    }
                    Date enddate = ForDate.getDates(edate);

                    System.out.println("Enter registration deadline (dd/mm/yyyy):");
                    String deadline = scand.nextLine();
                    while (ForDate.getDates(deadline) == null) {
                        deadline = scand.nextLine();
                    }
                    Date deadlines = ForDate.getDates(deadline);

                    System.out.println("Enter your location:");
                    location += scan.nextLine();

                    System.out.println("Enter your description:");
                    description += scan.nextLine();

                    System.out.println("Enter total number of slots:");
                    totalslot += sc.nextInt();

                    System.out.println("Enter the number of committee members allowed(max 10):");
                    commslot += sc.nextInt();

                    CampController.createCamp(name, startdate, enddate, deadlines, grp, location, totalslot, commslot,
                            description, loggedID);
                    System.out.println("Your camp has been created");
                    break;

                case 2:
                    scan = new Scanner(System.in);
                    System.out.println("Choose the camp you want to edit");
                    Integer input = sc.nextInt();
                    if (input >= getcampic.size())
                        System.out.println("No such suggestion");
                    else {
                        // note: let user choose info to edit
                    }
                    break;

                case 3:
                    System.out.println("Choose the camp you want to delete:");
                    input = sc.nextInt();
                    if (input >= getcampic.size())
                        System.out.println("No such camp");
                    else {
                        for (int j = 0; j < getcampic.size(); j++) {
                            if (input == j) {
                                CampController.deleteCamp(getcampid.get(j));
                                System.out.println("Camp deleted");
                            }
                        }

                    }
                    break;
                case 4: // toggle visibility of camp, reflected in camp list that will be visible to students
                    System.out.println("Choose the camp you want to toggle visibility:");
                    input = sc.nextInt();
                    if (input >= getcampic.size())
                        System.out.println("No such camp");
                    else {
                        System.out.println("Choose 0 to turn off, choose 1 to turn on:");
                        for (int j = 0; j < getcampic.size(); j++) {
                            if (input == j) {
                                Integer toggle = sc.nextInt();
                                if (toggle == 0) {
                                    CampController.updateCampVisiblity(getcampid.get(j), false);
                                    System.out.println("Camp visibility turned off");
                                }
                                else if (toggle == 1) {
                                    CampController.updateCampVisiblity(getcampid.get(j), true);
                                    System.out.println("Camp visibility turned on");
                                    
                                }
                                else System.out.println("Invalid input");
                                
                            }
                        }

                    }
                    break;

                case 5:
                    continues = false;
                    break;

                default:
                    System.out.println("Invalid!");
                    break;
            }
        }
        continues = true;
    }
}
