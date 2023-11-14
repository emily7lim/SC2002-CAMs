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
import report.ReportController;
import report.enums.ReportOutputType;
import report.enums.ReportType;

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

    public static void FileType(ArrayList<Camp> camps, FilterObj filtering, ReportType reportType) {
        ReportController rp = new ReportController();
        System.out.println("\nSelect your format\n1) .txt \n2) .csv");
        Boolean continues = true;
        while (continues) {
            Integer format = CommonUse.dataValidation();
            switch (format) {
                case 1: // txt
                    rp.generateAndWriteReports(camps, filtering, reportType, ReportOutputType.TXT);
                    continues = false;
                    break;

                case 2: // csv
                    rp.generateAndWriteReports(camps, filtering, reportType, ReportOutputType.CSV);
                    continues = false;
                    break;

                default:
                    System.out.print("Please select file format 1 or 2: ");
                    break;
            }
        }

    }

    public static void FilterReport(ArrayList<Camp> camps) {
        FilterObj filtering = new FilterObj();
        System.out.println("\nSelect what you want to be generated \n1) Attendee\n2) Camp committee\n3) All\n4) Quit");
        System.out.println("Enter your choice and select 4 to quit");
        Boolean continues = true;
        while (continues) {
            Integer filter = CommonUse.dataValidation();
            // report includes camp details n filtered results
            switch (filter) {
                case 1: // report includes attendee name
                    filtering.setSelectedAttendee(true);
                    break;

                case 2: // report includes comm name
                    filtering.setSelectedCampCommittee(true);
                    break;

                case 3: // include everything?
                    filtering.setAllCase5(true); // this sets all the vars u need for case 5
                    filtering.isAnyCase5();

                case 4:
                    continues = false;
                    break;

                default:
                    System.out.println("Invalid detail");
                    break;
            }

        }

        FileType(camps, filtering, ReportType.CAMP_DETAILS_REPORT);

    }
}
