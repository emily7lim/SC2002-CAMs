package view;

import java.util.*;
import java.text.SimpleDateFormat;

import controller.*;
import model.enums.Faculty;

public class StaffCreated {
    public static void StaffCreate(String loggedID) {
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
                if (CampController.getAllCamps().get(i).getStaffInCharge().equals(loggedID)) {
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
                    Faculty grp = StaffController.getStaffByUserId(loggedID).getFaculty();
                    Date startdate = null, enddate = null, deadlines = null;
                    Integer commslot = 0, totalslot = 0;

                    System.out.println("***********You are now creating***********");
                    System.out.println("Enter camp name:");
                    name += scan.nextLine();

                    System.out.println("Enter your start date (dd/mm/yyyy):");
                    Scanner scand = new Scanner(System.in);
                    Boolean wrong = true;
                    Date now = new Date();
                    String sdate = scand.nextLine();                    
                    startdate = ForDate.getDates(sdate);

                    while (wrong) {
                        if (startdate == null) {
                            sdate = scand.nextLine();
                            startdate = ForDate.getDates(sdate);

                        } else if (startdate.before(now)) {
                            System.out.println("Start date cannot be earlier than today");
                            sdate = scand.nextLine();
                            startdate = ForDate.getDates(sdate);
                        } else {
                            startdate = ForDate.getDates(sdate);
                            wrong = false;
                        }
                    }


                    System.out.println("Enter your end date (dd/mm/yyyy):");
                    scand = new Scanner(System.in);
                    wrong = true;
                    String edate = scand.nextLine();
                    enddate = ForDate.getDates(edate);
                                    
                    while (wrong) {
                        if (enddate == null) {
                            edate = scand.nextLine();
                            enddate = ForDate.getDates(edate);

                        } else if (enddate.before(startdate)) {
                            System.out.println("End date cannot be earlier than start date");
                            edate = scand.nextLine();
                            enddate = ForDate.getDates(edate);
                        } else {
                            enddate = ForDate.getDates(edate);
                            wrong = false;
                        }
                    }

                    System.out.println("Enter registration deadline (dd/mm/yyyy):");
                    String deadline = scand.nextLine();
                    deadlines = ForDate.getDates(deadline);
                    wrong = true; 

                    while (wrong) {
                        if (deadlines == null) {
                            deadline = scand.nextLine();
                            deadlines = ForDate.getDates(deadline);

                        } else if (deadlines.after(startdate)) {
                            System.out.println("Deadline date cannot be later than start date");
                            deadline = scand.nextLine();
                            deadlines = ForDate.getDates(deadline);
                        } else if (deadlines.before(now)) {
                            System.out.println("Deadline cannot be earlier than today");
                            deadline = scand.nextLine();
                            deadlines = ForDate.getDates(deadline);
                        }
                        else {
                            deadlines = ForDate.getDates(deadline);
                            wrong = false;
                        }
                    }

                    System.out.println("1) Open to all:\n2) Faculty only");
                    Integer input = CommonUse.dataValidation();
                    while (input > 2) {
                        System.out.println("Select 1 or 2:");
                        input = CommonUse.dataValidation();
                    }
                    if (input == 1)
                        grp = Faculty.NTU;

                    System.out.println("Enter your location:");
                    location += scan.nextLine();

                    System.out.println("Enter your description:");
                    description += scan.nextLine();

                    System.out.println("Enter total number of slots:");
                    totalslot = CommonUse.dataValidation();

                    System.out.println("Enter the number of committee members allowed(max 10):");
                    commslot = CommonUse.dataValidation();
                    while (commslot > 10) {
                        System.out.println("Max slot allowed is 10");
                        commslot = CommonUse.dataValidation();
                    }

                    CampController.createCamp(name, startdate, enddate, deadlines, grp, location, totalslot, commslot,
                            description, loggedID);
                    System.out.println("Your camp has been created");
                    break;

                case 2:
                    name = ""; location = ""; description = "";
                    startdate = null; enddate = null; deadlines = null;
                    grp = StaffController.getStaffByUserId(loggedID).getFaculty();
                    commslot = 0; totalslot = 0;
                    scan = new Scanner(System.in);
                    System.out.println("Choose the camp you want to edit");
                    input = CommonUse.dataValidation();
                    if (input >= getcampic.size())
                        System.out.println("No such camp");
                    else {

                        System.out.println("***********You are now editing***********");
                        System.out.println(
                                "1) Name\n2) Start date\n3) End date\n4) Registration deadline\n5) User group\n6) Location\n7) Total slots\n8) Comm slots\n9) Description");
                        for (int i = 0; i < getcampid.size(); i++) {
                            if (input == i) {
                                Integer edits = CommonUse.dataValidation();
                                switch (edits) {
                                    case 1:
                                        System.out.println("Update camp name");
                                        name += scan.nextLine();
                                        CampController.updateCampName(getcampid.get(i), name);

                                        break;
                                    case 2:
                                        System.out.println("Update camp start date");
                                        scand = new Scanner(System.in);
                                        sdate = scand.nextLine();
                                        while (ForDate.getDates(sdate) == null) {
                                            sdate = scand.nextLine();
                                        }
                                        startdate = ForDate.getDates(sdate);
                                        CampController.updateCampStartDate(getcampid.get(i), startdate);
                                        break;
                                    case 3:
                                        System.out.println("Update camp end date");
                                        scand = new Scanner(System.in);
                                        edate = scand.nextLine();
                                        while (ForDate.getDates(edate) == null) {
                                            edate = scand.nextLine();
                                        }
                                        enddate = ForDate.getDates(edate);
                                        CampController.updateCampStartDate(getcampid.get(i), enddate);

                                        break;
                                    case 4:
                                        System.out.println("Update camp registration deadline");
                                        scand = new Scanner(System.in);
                                        deadline = scand.nextLine();
                                        while (ForDate.getDates(deadline) == null) {
                                            deadline = scand.nextLine();
                                        }
                                        deadlines = ForDate.getDates(deadline);
                                        CampController.updateCampStartDate(getcampid.get(i), deadlines);
                                        break;
                                    case 5:
                                        System.out.println("Update camp group");
                                        // grp += scan.nextLine();
                                        // CampController.updateCampUserGroup(getcampid.get(i), grp);
                                        break;
                                    case 6:
                                        System.out.println("Update camp location");
                                        location += scan.nextLine();
                                        CampController.updateCampLocation(getcampid.get(i), location);
                                        break;
                                    case 7:
                                        System.out.println("Update camp slots");
                                        totalslot = CommonUse.dataValidation();
                                        CampController.updateCampTotalSlots(getcampid.get(i), totalslot);
                                        break;
                                    case 8:
                                        System.out.println("Update camp slots");
                                        commslot = CommonUse.dataValidation();
                                        while (commslot > 10) {
                                            System.out.println("Max slot allowed is 10");
                                            commslot = CommonUse.dataValidation();
                                        }
                                        CampController.updateCampCommSlots(getcampid.get(i), commslot);
                                        break;
                                    case 9:
                                        description += scan.nextLine();
                                        CampController.updateCampDescription(getcampid.get(i),
                                                description);
                                        break;

                                    default:
                                        break;
                                }
                                // CampController.updateCampInformation(getcampid.get(i), name, startdate, enddate, deadlines, grp, location, totalslot, commslot, description);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Choose the camp you want to delete:");
                    input = CommonUse.dataValidation();
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
                case 4: // toggle visibility of camp, reflected in camp list that will be visible to
                        // students
                    System.out.println("Choose the camp you want to toggle visibility:");
                    input = CommonUse.dataValidation();
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
                                } else if (toggle == 1) {
                                    CampController.updateCampVisiblity(getcampid.get(j), true);
                                    System.out.println("Camp visibility turned on");

                                } else
                                    System.out.println("Invalid input");

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
