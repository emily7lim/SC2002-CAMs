package view;

import java.text.SimpleDateFormat;
import java.util.*;

import controller.CampController;
import controller.EnquiryController;
import controller.StaffController;
import controller.StudentController;
import controller.SuggestionController;
import model.*;
import model.enums.EnquiryStatus;
import model.enums.Faculty;
import model.enums.Role;
import model.enums.SuggestionStatus;
import report.ReportController;
import report.enums.ReportOutputType;
import report.enums.ReportType;

public class CommonUse {
    public String getWhitespace(int total, int length) {
        return String.format("%" + (total - length) / 2 + "s", "");
    }

    public String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public void printDivider(int type) {
        System.out.println(type == 1 ? "======================================================================"
                : "----------------------------------------------------------------------");
    }

    public void printCampDetails(Camp camp, int index) {
        printDivider(1);
        String name = String.format("Camp %d: %s", index, camp.getName());
        System.out.println(getWhitespace(70, name.length()) + "\u001B[1m" + name + "\u001B[0m");
        printDivider(1);

        String description = camp.getDescription();
        while (!description.equals("")) {
            int position = description.lastIndexOf(" ", 67) != -1 && description.length() > 66
                    ? description.lastIndexOf(" ", 67)
                    : description.length();
            String temp = description.substring(0, position);
            System.out.println(getWhitespace(70, temp.length()) + temp);
            description = position == description.length() ? "" : description.substring(position + 1);
        }
        printDivider(1);

        System.out.printf("  Date: \t\t   %s - %s%n", formatDate(camp.getStartDate()), formatDate(camp.getEndDate()));
        System.out.printf("  Registration Deadline:   %s%n", formatDate(camp.getRegistrationCloseDate()));
        System.out.printf("  Location: \t\t   %s%n", camp.getLocation());
        System.out.printf("  Participant Slots Left:  %d/%d%n", camp.getRemainingParticipantSlots(), camp.getParticipantSlots());
        System.out.printf("  Committee Slots Left:    %d/%d%n", camp.getRemainingCommitteeSlots(), camp.getCommSlots());
        System.out.printf("  User Group: \t\t   %s%n", camp.getUserGroup().getFaculty());
        System.out.printf("  Camp IC: \t\t   %s%n", camp.getStaffInCharge());
        System.out.printf("  Visibility: \t\t   %s%n", camp.isVisible() ? "Visible" : "Not Visible");
    }

    public void printCampParticipants(Camp camp, int index) {
        printDivider(1);
        String name = String.format("Camp %d: %s", index, camp.getName());
        System.out.println(getWhitespace(70, name.length()) + "\u001B[1m" + name + "\u001B[0m");
        printDivider(1);

        ArrayList<String> participants = camp.getParticipantIds();
        ArrayList<String> committee = camp.getCommitteeIds();

        System.out.printf("%11sParticipants%11s|%13sCommittee%n", "", "", "");
        printDivider(2);
        System.out.printf("%34s|%35s%n", "", "");

        if (participants.isEmpty())
            System.out.printf("%4sNo participants registered%4s|", "", "");
        else {
            System.out.printf("%s- %s%s|", getWhitespace(34, participants.get(0).length() + 2), participants.get(0),
                    getWhitespace(34, participants.get(0).length() + 2));
            participants.remove(0);
        }
        if (committee.isEmpty())
            System.out.printf("%6sNo committee registered%6s%n", "", "");
        else {
            System.out.printf("%s- %s%s%n", getWhitespace(35, committee.get(0).length() + 2), committee.get(0),
                    getWhitespace(35, committee.get(0).length() + 2));
            committee.remove(0);
        }

        while (!participants.isEmpty() && !committee.isEmpty()) {
            System.out.printf("%s- %s%s|%s- %s%s%n", getWhitespace(34, participants.get(0).length() + 2),
                    participants.get(0), getWhitespace(34, participants.get(0).length() + 2),
                    getWhitespace(35, committee.get(0).length() + 2), committee.get(0),
                    getWhitespace(35, committee.get(0).length() + 2));
            participants.remove(0);
            committee.remove(0);
        }

        if (!participants.isEmpty())
            for (String participantId : participants)
                System.out.printf("%s- %s%s|%n", getWhitespace(34, participantId.length() + 2), participantId,
                        getWhitespace(34, participantId.length() + 2));
        if (!committee.isEmpty())
            for (String committeeId : committee)
                System.out.printf("%s- %s%s|%n", getWhitespace(35, committeeId.length() + 2), committeeId,
                        getWhitespace(35, committeeId.length() + 2));

        System.out.printf("%34s|%35s%n", "", "");
    }

    public void printSuggestionDetails(Suggestion suggestion) {
        boolean shown = false;
        String message = suggestion.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 45) != -1 && message.length() > 44
                    ? message.lastIndexOf(" ", 45)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-44s | %-8s | %-10s %n", temp, shown ? "" : suggestion.getStatus().getSuggestionStatus(),
                    shown ? "" : suggestion.getCreatorId());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }

    }

    public void printSuggestionDetailsWithIndex(Suggestion suggestion, int index) {
        boolean shown = false;
        String message = suggestion.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 39) != -1 && message.length() > 38
                    ? message.lastIndexOf(" ", 39)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-3s | %-38s | %-8s | %-10s %n", shown ? "" : index, temp, shown ? "" : suggestion.getStatus().getSuggestionStatus(),
                    shown ? "" : suggestion.getCreatorId());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printEnquiryDetails(Enquiry enquiry) {
        boolean shown = false;
        String message = enquiry.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 46) != -1 && message.length() > 45
                    ? message.lastIndexOf(" ", 46)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-45s | %-7s | %-10s %n", temp, shown ? "" : enquiry.getStatus().getEnquiryStatus(),
                    shown ? "" : enquiry.getCreatorId());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printEnquiryDetailsWithIndex(Enquiry enquiry , int index) {
        boolean shown = false;
        String message = enquiry.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 40) != -1 && message.length() > 39
                    ? message.lastIndexOf(" ", 40)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-3s | %-39s | %-7s | %-10s %n", shown ? "" : index, temp, shown ? "" : enquiry.getStatus().getEnquiryStatus(),
                    shown ? "" : enquiry.getCreatorId());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printEnquiryDetailsWithReply(Enquiry enquiry) {
        String message = enquiry.getMessage();
        String reply = enquiry.getReply();

        printDivider(2);
        while (!message.equals("") || !reply.equals("")) {
            int messagePosition = message.lastIndexOf(" ", 28) != -1 && message.length() > 27
                    ? message.lastIndexOf(" ", 28)
                    : message.length();
            String tempMessage = message.substring(0, messagePosition);

            int replyPosition = reply.lastIndexOf(" ", 39) != -1 && reply.length() > 38
                    ? reply.lastIndexOf(" ", 39)
                    : reply.length();
            String tempReply = reply.substring(0, replyPosition);

            System.out.printf(" %-27s | %-38s %n", tempMessage, tempReply);
            message = messagePosition == message.length() ? "" : message.substring(messagePosition + 1);
            reply = replyPosition == reply.length() ? "" : reply.substring(replyPosition + 1);
        }
    }

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
                    ReportController.generateAndWriteReports(camps, filtering, reportType, ReportOutputType.TXT);
                    continues = false;
                    break;

                case 2: // csv
                    ReportController.generateAndWriteReports(camps, filtering, reportType, ReportOutputType.CSV);
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
