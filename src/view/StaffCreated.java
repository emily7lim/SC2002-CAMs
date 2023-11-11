package view;

import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import controller.CampController;
import model.enums.Faculty;

public class StaffCreated {
    public static void main(String[] args) throws ParseException {
        CampController campc = new CampController();
        ForDate dates = new ForDate();
        String loggedID = "ARVI"; // note:take from login detail

        Scanner sc = new Scanner(System.in);
        Boolean continues = true;

        SimpleDateFormat strdate = new SimpleDateFormat("dd/MM/yyyy");

        while (continues) {
            System.out.println("______________\n");
            System.out.println("Created Camps");
            System.out.println("______________");

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

            for (int i = 0; i < campc.getAllCamps().size(); i++) {
                if (campc.getAllCamps().get(i).getStaffInCharge() == loggedID) {
                    getcamplocation.add(campc.getAllCamps().get(i).getLocation());
                    getcampname.add(campc.getAllCamps().get(i).getName());
                    getcampdesc.add(campc.getAllCamps().get(i).getDescription());
                    getcampgrp.add(campc.getAllCamps().get(i).getUserGroup());
                    getcampic.add(campc.getAllCamps().get(i).getStaffInCharge());
                    getcampslot.add(campc.getAllCamps().get(i).getTotalSlots());
                    getcampcommslot.add(campc.getAllCamps().get(i).getCommSlots());
                    getcampsdate.add(campc.getAllCamps().get(i).getStartDate());
                    getcampedate.add(campc.getAllCamps().get(i).getEndDate());
                    getcampdeadline.add(campc.getAllCamps().get(i).getRegistrationCloseDate());
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
                        getcampdesc.get(i));

            }
            System.out.println("\n1) Create\n2) Edit\n3) Delete\n4) Quit");
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
                    while (dates.getDates(sdate) == null) {
                        sdate = scand.nextLine();
                    }
                    Date startdate = dates.getDates(sdate);

                    System.out.println("Enter your end date (dd/mm/yyyy):");
                    String edate = scand.nextLine();
                    while (dates.getDates(edate) == null) {
                        edate = scand.nextLine();
                    }
                    Date enddate = dates.getDates(edate);

                    System.out.println("Enter registration deadline (dd/mm/yyyy):");
                    String deadline = scand.nextLine();
                    while (dates.getDates(deadline) == null) {
                        deadline = scand.nextLine();
                    }
                    Date deadlines = dates.getDates(deadline);

                    System.out.println("Enter your location:");
                    location += scan.nextLine();

                    System.out.println("Enter your description:");
                    description += scan.nextLine();

                    System.out.println("Enter total number of slots:");
                    totalslot += sc.nextInt();

                    System.out.println("Enter the number of committee members allowed(max 10):");
                    commslot += sc.nextInt();

                    campc.createCamp(name, startdate, enddate, deadlines, grp, location, totalslot, commslot,
                            description, loggedID);
                    System.out.println("Your camp has been created");
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
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
