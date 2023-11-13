package view;

import java.text.SimpleDateFormat;
import java.util.*;

import controller.CampController;
import controller.EnquiryController;
import controller.StaffController;
import controller.StudentController;
import controller.SuggestionController;
import model.Camp;
import model.enums.EnquiryStatus;
import model.enums.Faculty;
import model.enums.Role;
import model.enums.SuggestionStatus;

public class CommonUse {
    public void ViewingCamps(String loggedID) {

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

            try {
                // student, visible, faculty match
                if (CampController.getAllCamps().get(i).isVisible() && CampController.getAllCamps().get(i)
                        .getUserGroup() == StudentController.getStudentByUserId(loggedID).getFaculty()) {
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

            } catch (Exception e) {
                // staff can view all
                if (StaffController.getStaffByUserId(loggedID).getRole() == Role.STAFF) {
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

        }

        SimpleDateFormat strdate = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < getcampic.size(); i++) {
            System.out.println("\n*****Details of camp " + i + "*******");
            System.out
                    .println("Camp Name: " + getcampname.get(i) + "\nStart date: " + strdate.format(getcampsdate.get(i))
                            + "\nEnd date: " + strdate.format(getcampedate.get(i)) + "\nRegistration deadline: "
                            + strdate.format(getcampdeadline.get(i)) + "\nUser group: " + getcampgrp.get(i)
                            + "\nLocation: " + getcamplocation.get(i) + "\nTotal slots: " + getcampslot.get(i)
                            + "\nCommittee slots: " + getcampcommslot.get(i) + "\nCamp IC: " + getcampic.get(i)
                            + "\nDescription: " + getcampdesc.get(i) + "\nVisibility: " + getcampvisible.get(i));

        }
    }

    public void ViewReply() {
        ArrayList<String> getenquiryreply = new ArrayList<>();
        ArrayList<String> getenquirymsg = new ArrayList<>();
        for (int i = 0; i < EnquiryController.getAllEnquiries().size(); i++) {
            if (EnquiryController.getAllEnquiries().get(i).getStatus() == EnquiryStatus.CLOSED) {
                getenquiryreply.add(EnquiryController.getAllEnquiries().get(i).getReply());
                getenquirymsg.add(EnquiryController.getAllEnquiries().get(i).getMessage());
            }
        }
        for (int i = 0; i < getenquiryreply.size(); i++) {

            System.out.println(i + ") " + getenquirymsg.get(i) + "-->" + getenquiryreply.get(i));

        }
    }

    public void Result() {
        Boolean continues = true;
        while (continues) {
            System.out.println("1) View replies to enquiries\n2) View approved/rejected suggestions\n3) Quit");
            Scanner sc = new Scanner(System.in);
            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // enquiry
                    System.out.println("These are the results of the enquiries");
                    ViewReply();
                    break;

                case 2: // suggestion
                    ArrayList<String> getsuggestmsg = new ArrayList<>();
                    for (int i = 0; i < SuggestionController.getAllSuggestions().size(); i++) {
                        if (SuggestionController.getAllSuggestions().get(i).getStatus() == SuggestionStatus.ACCEPTED) {

                            getsuggestmsg.add(
                                    SuggestionController.getAllSuggestions().get(i).getMessage() + " --> APPROVED");
                        } else if (SuggestionController.getAllSuggestions().get(i)
                                .getStatus() == SuggestionStatus.REJECTED) {

                            getsuggestmsg.add(
                                    SuggestionController.getAllSuggestions().get(i).getMessage() + " --> REJECTED");
                        }

                    }
                    System.out.println("These are the results of the suggestions");
                    for (int i = 0; i < getsuggestmsg.size(); i++) {

                        System.out.println(i + ") " + getsuggestmsg.get(i));

                    }
                    break;

                case 3:
                    continues = false;
                    break;

                default:
                    System.out.println("Please try agian");
                    break;
            }
        }
    }

    public static Integer dataValidation() {
        Scanner sc = new Scanner(System.in);
        Boolean validation = sc.hasNextInt();

        while (!validation) {
            System.out.println("Please input integer");
            sc = new Scanner(System.in);
            validation = sc.hasNextInt();
        }
        Integer validate = sc.nextInt();

        return validate;
    }
}
