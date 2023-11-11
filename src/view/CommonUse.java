package view;

import java.text.SimpleDateFormat;
import java.util.*;

import controller.CampController;
import controller.EnquiryController;
import controller.SuggestionController;
import model.enums.EnquiryStatus;
import model.enums.Faculty;
import model.enums.SuggestionStatus;

public class CommonUse {
    public void ViewingCamps() {
        CampController campc = new CampController();

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

        SimpleDateFormat strdate = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < getcamplocation.size(); i++) {
            System.out.println("\n*****Details of camp " + i + "*******");
            System.out.println("Camp Name: " + getcampname.get(i) + "\nStart date: " +
                    strdate.format(getcampsdate.get(i)) + "\nEnd date: "
                    + strdate.format(getcampedate.get(i)) + "\nRegistration deadline: "
                    + strdate.format(getcampdeadline.get(i)) + "\nUser group: " + getcampgrp.get(i)
                    + "\nLocation: " +
                    getcamplocation.get(i) + "\nTotal slots: " + getcampslot.get(i) + "\nCommittee slots: "
                    + getcampcommslot.get(i) + "\nCamp IC: " + getcampic.get(i) + "\nDescription: " +
                    getcampdesc.get(i));
        }
    }

    public void ViewReply() {
        EnquiryController enquiryc = new EnquiryController();
        ArrayList<String> getenquiryreply = new ArrayList<>();
        ArrayList<String> getenquirymsg = new ArrayList<>();
        for (int i = 0; i < enquiryc.getAllEnquiries().size(); i++) {
            if (enquiryc.getAllEnquiries().get(i).getStatus() == EnquiryStatus.CLOSED) {
                getenquiryreply.add(enquiryc.getAllEnquiries().get(i).getReply());
                getenquirymsg.add(enquiryc.getAllEnquiries().get(i).getMessage());
            }
        }
        for (int i = 0; i < getenquiryreply.size(); i++) {

            System.out
                    .println(i + ") " + getenquirymsg.get(i) + "-->" + getenquiryreply.get(i));

        }
    }

    public void Result() {
        SuggestionController suggestc = new SuggestionController();
        Boolean continues = true;
        while (continues) {
            System.out.println("1) View replies to enquiries\n2) View approved/rejected suggestions\n3) Quit");
            Scanner sc = new Scanner(System.in);
            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // enquiry
                    ViewReply();
                    break;

                case 2: // suggestion
                    ArrayList<String> getsuggestmsg = new ArrayList<>();
                    for (int i = 0; i < suggestc.getAllSuggestions().size(); i++) {
                        if (suggestc.getAllSuggestions().get(i).getStatus() == SuggestionStatus.ACCEPTED) {

                            getsuggestmsg.add(suggestc.getAllSuggestions().get(i).getMessage() + " --> APPROVED");
                        } else if (suggestc.getAllSuggestions().get(i).getStatus() == SuggestionStatus.REJECTED) {

                            getsuggestmsg.add(suggestc.getAllSuggestions().get(i).getMessage() + " --> REJECTED");
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
}