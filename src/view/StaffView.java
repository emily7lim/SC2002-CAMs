package view;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Suggestion;
import report.enums.ReportType;
import utils.HelperUtil;

import java.util.*;

public class StaffView extends MainView {
    private final String MENU_TITLE = "Staff Menu";

    private String userId;
    private CommonUse common;
    private CreatedCampsView createdCampsView;

    public StaffView() {
        common = new CommonUse();
        createdCampsView = new CreatedCampsView();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View All Camps/Participants");
        System.out.println("  2)  Manage Created Camps");
        System.out.println("  3)  Manage Camp Enquiries");
        System.out.println("  4)  Manage Camp Suggestions");
        System.out.println("  5)  Generate Camp Participants/Committee Report");
        System.out.println("  6)  Generate Camp Committee Performance Report");
        System.out.println("  7)  Profile");
        System.out.println("  8)  Change password");
        System.out.println("  9)  Logout");
    }

    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 9);

            switch (choice) {
                case 1:
                    viewAllCampsParticipantsMenu();
                    printMenu();
                    break;

                case 2: // see list of camps that he/she created in separate menu list so they can edit
                    createdCampsView.setUserId(userId);
                    createdCampsView.setCommon(common);

                    createdCampsView.viewMenu();
                    printMenu();
                    break;

                case 3: // view&reply enquiries from students to the camps the staff created
                    manageCampEnquiries();
                    printMenu();
                    break;

                case 4: // Manage Suggestions for Created Camps
                    manageCampSuggestions();
                    printMenu();
                    break;

                case 5: // generate report of list of students attending each camp the staff created.
                        // list will include details of camp n roles of participants
                    generateCampParticipantsCommitteeReport();
                    printMenu();
                    break;

                case 6: // performance report of camp comm members
                    generateCampCommitteePerformanceReport();
                    printMenu();
                    break;

                case 7: // profile
                    // TODO: Link to Profile
                    break;

                case 8: // change password
                    changePassword();
                    printMenu();
                    break;

                case 9:
                    break;

                default:
                    break;
            }
        } while (choice != 9);
    }

    public void viewAllCampsParticipantsMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Camps/Participants");
        System.out.println("  1)  View All Camp Details\n  2)  View All Camp Participants/Committee\n  3)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewAllCampDetails();
                    break;
                case 2:
                    viewAllCampParticipants();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void viewAllCampDetails() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Camps");

        ArrayList<Camp> campList = CampController.getAllCamps();
        for (int i = 0; i < campList.size(); i++)
            common.printCampDetails(campList.get(i), i + 1);
    }

    public void viewAllCampParticipants() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Camp Participants/Committee");

        ArrayList<Camp> campList = CampController.getAllCamps();
        for (int i = 0; i < campList.size(); i++)
            common.printCampParticipants(campList.get(i), i + 1);
    }

    public void manageCampEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Camp Enquiries");

        System.out.println(
                "  1)  View All Camp Enquiries\n  2)  View All Replied Camp Enquiries\n  3)  Manage Pending Camp Enquiries\n  4)  Generate Camp Enquiry Report\n  5)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 5);

            switch (choice) {
                case 1:
                    viewAllCampEnquiries();
                    break;
                case 2:
                    viewAllRepliedCampEnquiries();
                    break;
                case 3:
                    managePendingCampEnquiries();
                    break;
                case 4:
                    generateCampEnquiriesReport();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void viewAllCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Enquiries");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%38s | Status  | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getEnquiriesByCampId(camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printEnquiryDetails(campEnquiries.get(i));
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No enquiries found\n");
        }
    }

    public void viewAllRepliedCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Replied Enquiries");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%20s | Response%n", "", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getRepliedEnquiriesByCampId(camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printEnquiryDetailsWithReply(campEnquiries.get(i));
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No enquiries found\n");
        }
    }

    public void managePendingCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Enquiries");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" No. | Enquiry%32s | Status  | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getPendingEnquiriesByCampId(camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printEnquiryDetailsWithIndex(campEnquiries.get(i), enquiries.size() + i + 1);
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No pending suggestions found\n");
            return;
        }

        int choice = -1, index;
        System.out.println("\n  1)  Reply Enquiry\n  2)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 2);

            switch (choice) {
                case 1:
                    index = -1;
                    do {
                        System.out.print("Select an Enquiry to reply: ");
                        index = HelperUtil.nextInt(1, enquiries.size());
                    } while (index == -1);

                    replyEnquiry(enquiries.get(index - 1), index);
                    choice = 2;
                    break;

                case 2:
                    break;
                default:
                    break;
            }
        } while (choice != 2);
    }

    public void replyEnquiry(Enquiry enquiry, int index) {
        String reply = "";
        HelperUtil.clearScreen();
        printMenuTitle("Reply Enquiry " + index);

        System.out.printf(" Enquiry%38s | Status  | Creator%n", "");
        common.printEnquiryDetails(enquiry);

        do {
            System.out.print("\nEnter your reply: ");
            reply = HelperUtil.nextString();

            if (reply.equals(""))
                System.out.println("Invalid reply, please try again.");
            else {
                EnquiryController.replyEnquiry(enquiry.getEnquiryId(), reply, userId);
                System.out.println("\nEnquiry successfully replied.");
            }
        } while (reply.equals(""));

        HelperUtil.pressAnyKeyToContinue();
        managePendingCampEnquiries();
    }

    public void generateCampEnquiriesReport() {
        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        CommonUse.FileType(camps, null, ReportType.ENQUIRIES_REPORT);

        System.out.println("\nCamp Enquiries Report generated successfully.");
    }

    public void generateCampParticipantsCommitteeReport() {
        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        CommonUse.FilterReport(camps);

        System.out.println("\nCamp Participants/Committee Report generated successfully.");
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void generateCampCommitteePerformanceReport() {
        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        CommonUse.FileType(camps, null, ReportType.PERFORMANCE_REPORT);

        System.out.println("\nCamp Committee Performance Report generated successfully.");
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void manageCampSuggestions() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Camp Suggestions");
        System.out.println(
                "  1)  View All Camp Suggestions\n  2)  View All Approved/Rejected Camp Suggestions\n  3)  Manage Pending Camp Suggestions\n  4)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 4);

            switch (choice) {
                case 1:
                    viewAllCampSuggestions();
                    break;
                case 2:
                    viewAllApprovedRejectedCampSuggestions();
                    break;
                case 3:
                    managePendingCampSuggestions();
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void viewAllCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Suggestions");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        System.out.printf(" Suggestion%34s | Status   | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Suggestion> campSuggestions = SuggestionController.getSuggestionsByCampId(camp.getCampId());
            if (campSuggestions.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campSuggestions.size(); i++)
                common.printSuggestionDetails(campSuggestions.get(i));
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No suggestions found\n");
        }
    }

    public void viewAllApprovedRejectedCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Approved/Rejected Suggestions");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        System.out.printf(" Suggestion%34s | Status   | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Suggestion> campSuggestions = SuggestionController
                    .getApprovedRejectedSuggestionsByCampId(camp.getCampId());
            if (campSuggestions.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campSuggestions.size(); i++)
                common.printSuggestionDetails(campSuggestions.get(i));
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No suggestions found\n");
        }
    }

    public void managePendingCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Suggestions");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        System.out.printf(" No. | Suggestion%28s | Status   | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Suggestion> campSuggestions = SuggestionController
                    .getPendingSuggestionsByCampId(camp.getCampId());
            if (campSuggestions.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campSuggestions.size(); i++)
                common.printSuggestionDetailsWithIndex(campSuggestions.get(i), suggestions.size() + i + 1);
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No pending suggestions found\n");
            return;
        }

        int choice = -1, index;
        System.out.println("\n  1)  Approve Suggestion\n  2)  Reject suggestion\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    index = -1;
                    do {
                        System.out.print("Select a Suggestion to approve: ");
                        index = HelperUtil.nextInt(1, suggestions.size());
                    } while (index == -1);

                    approveSuggestion(suggestions.get(index - 1), index);
                    choice = 3;
                    break;

                case 2:
                    index = -1;
                    do {
                        System.out.print("Select a Suggestion to reject: ");
                        index = HelperUtil.nextInt(1, suggestions.size());
                    } while (index == -1);

                    rejectSuggestion(suggestions.get(index - 1), index);
                    choice = 3;
                    break;

                case 3:
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }

    public void approveSuggestion(Suggestion suggestion, int index) {
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Approve Suggestion " + index);

        System.out.printf(" Suggestion%34s | Status   | Creator%n", "");
        common.printSuggestionDetails(suggestion);

        do {
            System.out.print("\nAre you sure you want to approve this suggestion? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                SuggestionController.acceptSuggestion(suggestion.getSuggestionId(), userId, suggestion.getCreatorId());
                StudentController.addPoint(suggestion.getCreatorId());
                System.out.println("Suggestion successfully approved.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
        managePendingCampSuggestions();
    }

    public void rejectSuggestion(Suggestion suggestion, int index) {
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Reject Suggestion " + index);

        System.out.printf(" Suggestion%34s | Status   | Creator%n", "");
        common.printSuggestionDetails(suggestion);

        do {
            System.out.print("\nAre you sure you want to reject this suggestion? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                SuggestionController.rejectSuggestion(suggestion.getSuggestionId(), userId);
                System.out.println("Suggestion successfully rejected.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
        managePendingCampSuggestions();
    }

    public void changePassword() {
        String currentPassword, newPassword, confirmPassword;
        HelperUtil.clearScreen();
        printMenuTitle("Change Password");

        do {
            System.out.print("Enter your current password (Enter b to go back): ");
            currentPassword = HelperUtil.nextString();
            if (currentPassword.equals("b"))
                break;

            System.out.print("Enter your new password: ");
            newPassword = HelperUtil.nextString();

            System.out.print("Re-enter your new password: ");
            confirmPassword = HelperUtil.nextString();

            if (!UserController.validateUserCredentials(userId, currentPassword)) {
                System.out.println("\nCurrent password is incorrect, please try again.");
            } else if (!newPassword.equals(confirmPassword)) {
                System.out.println("\nPasswords do not match, please try again.");
            } else {
                UserController.changePassword(userId, newPassword);
                System.out.println("\nPassword successfully updated.");
                HelperUtil.pressAnyKeyToContinue();
                break;
            }
        } while (true);

        HelperUtil.clearScreen();
    }
}