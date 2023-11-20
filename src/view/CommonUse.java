package view;

import java.text.SimpleDateFormat;
import java.util.*;

import model.*;
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
        System.out.printf("  Participant Slots Left:  %d/%d%n", camp.getRemainingParticipantSlots(),
                camp.getParticipantSlots());
        System.out.printf("  Committee Slots Left:    %d/%d%n", camp.getRemainingCommitteeSlots(), camp.getCommSlots());
        System.out.printf("  User Group: \t\t   %s%n", camp.getUserGroup().getFaculty());
        System.out.printf("  Camp IC: \t\t   %s%n", camp.getStaffInCharge());
        System.out.printf("  Visibility: \t\t   %s%n", camp.isVisible() ? "Visible" : "Not Visible");
    }

    public void printCampDetailsWithRole(Camp camp, int index, String userId) {
        printCampDetails(camp, index);
        if (camp.getParticipantIds().contains(userId) || camp.getCommitteeIds().contains(userId)
                || camp.getWithdrawnParticipantIds().contains(userId))
            System.out.printf("  Registered as: \t   %s%n",
                    camp.getParticipantIds().contains(userId) ? "Participant"
                            : camp.getCommitteeIds().contains(userId) ? "Committee" : "Withdrawn");
        else
            System.out.printf("%n");
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

            System.out.printf(" %-44s | %-8s | %-10s %n", temp,
                    shown ? "" : suggestion.getStatus().getSuggestionStatus(),
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

            System.out.printf(" %-3s | %-38s | %-8s | %-10s %n", shown ? "" : index, temp,
                    shown ? "" : suggestion.getStatus().getSuggestionStatus(),
                    shown ? "" : suggestion.getCreatorId());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printUserSuggestionDetails(Suggestion suggestion) {
        boolean shown = false;
        String message = suggestion.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 45) != -1 && message.length() > 44
                    ? message.lastIndexOf(" ", 45)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-57s | %-8s %n", temp,
                    shown ? "" : suggestion.getStatus().getSuggestionStatus());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printUserSuggestionDetailsWithIndex(Suggestion suggestion, int index) {
        boolean shown = false;
        String message = suggestion.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 39) != -1 && message.length() > 38
                    ? message.lastIndexOf(" ", 39)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-3s | %-51s | %-8s %n", shown ? "" : index, temp,
                    shown ? "" : suggestion.getStatus().getSuggestionStatus());
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

    public void printEnquiryDetailsWithIndex(Enquiry enquiry, int index) {
        boolean shown = false;
        String message = enquiry.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 40) != -1 && message.length() > 39
                    ? message.lastIndexOf(" ", 40)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-3s | %-39s | %-7s | %-10s %n", shown ? "" : index, temp,
                    shown ? "" : enquiry.getStatus().getEnquiryStatus(),
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

    public void printUserEnquiryDetails(Enquiry enquiry) {
        boolean shown = false;
        String message = enquiry.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 46) != -1 && message.length() > 45
                    ? message.lastIndexOf(" ", 46)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-58s | %-7s %n", temp, shown ? "" : enquiry.getStatus().getEnquiryStatus());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
        }
    }

    public void printUserEnquiryDetailsWithIndex(Enquiry enquiry, int index) {
        boolean shown = false;
        String message = enquiry.getMessage();

        printDivider(2);
        while (!message.equals("")) {
            int position = message.lastIndexOf(" ", 40) != -1 && message.length() > 39
                    ? message.lastIndexOf(" ", 40)
                    : message.length();
            String temp = message.substring(0, position);

            System.out.printf(" %-3s | %-52s | %-7s %n", shown ? "" : index, temp,
                    shown ? "" : enquiry.getStatus().getEnquiryStatus());
            shown = true;
            message = position == message.length() ? "" : message.substring(position + 1);
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

        sc.close();
        return validate;
    }

    public static void FileType(ArrayList<Camp> camps, FilterObj filtering, ReportType reportType) {
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
