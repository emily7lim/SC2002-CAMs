package view;

import java.util.ArrayList;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Suggestion;
import report.enums.ReportType;
import utils.HelperUtil;

public class CommitteeView extends StudentView {
    private final String MENU_TITLE = "Committee Menu";

    public CommitteeView() {
        super();
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View All Camps");
        System.out.println("  2)  View Registered Camps");
        System.out.println("  3)  Register for Camp");
        System.out.println("  4)  Withdraw from Camp");
        System.out.println("  5)  Manage Enquiries");
        System.out.println("  6)  Manage Suggestions");
        System.out.println("  7)  Manage Student Camp Enquiries");
        System.out.println("  8)  Generate Camp Participants/Committee Report");
        System.out.println("  9)  Profile");
        System.out.println("  10) Change Password");
        System.out.println("  11) Logout");
    }

    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 11);

            switch (choice) {
                case 1:
                    viewAllCamps();
                    printMenu();
                    break;
                case 2:
                    viewRegisteredCamps();
                    printMenu();
                    break;
                case 3:
                    registerForCamps();
                    printMenu();
                    break;
                case 4:
                    withdrawFromCamps();
                    printMenu();
                    break;
                case 5:
                    manageEnquiries();
                    printMenu();
                    break;
                case 6:
                    manageCampSuggestions();
                    printMenu();
                    break;
                case 7:
                    manageStudentCampEnquiries();
                    printMenu();
                    break;
                case 8:
                    generateCampParticipantsCommitteeReport();
                    printMenu();
                    break;
                case 9:
                    viewProfile();
                    printMenu();
                    break;
                case 10:
                    changePassword();
                    printMenu();
                    break;
                case 11:
                    break;
                default:
                    break;
            }
        } while (choice != 11);
    }

    public void manageStudentCampEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Camp Enquiries");

        System.out.println(
                "  1)  Manage Pending Camp Enquiries\n  2)  Generate Camp Enquiry Report\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    managePendingCampEnquiries();
                    break;
                case 2:
                    generateCampEnquiriesReport();
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

    public void managePendingCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Enquiries");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Enquiry> enquiries = EnquiryController.getPendingEnquiriesByCampId(camp.getCampId());

        System.out.printf(" No. | Enquiry%32s | Status  | Creator%n", "");
        if (enquiries.size() != 0) {
            common.printDivider(2);
            System.out.printf(" %s%n", camp.getName());
        } else {
            common.printDivider(2);
            System.out.println(" No pending suggestions found.\n");
            return;
        }

        for (int i = 0; i < enquiries.size(); i++)
            common.printEnquiryDetailsWithIndex(enquiries.get(i), i + 1);

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
                StudentController.addPoint(userId);
                System.out.println("\nEnquiry successfully replied.");
            }
        } while (reply.equals(""));

        HelperUtil.pressAnyKeyToContinue();
        managePendingCampEnquiries();
    }

    public void generateCampEnquiriesReport() {
        ArrayList<Camp> camps = CampController.getCommitteeCamps(userId);
        CommonUse.FileType(camps, null, ReportType.ENQUIRIES_REPORT);

        System.out.println("\nCamp Enquiries Report generated successfully.");
    }

    public void manageCampSuggestions() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Camp Suggestions");
        System.out.println(
                "  1)  View All Camp Suggestions\n  2)  View All Approved/Rejected Camp Suggestions\n  3)  Submit New Suggestion\n  4)  Manage Pending Camp Suggestions\n  5)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 5);

            switch (choice) {
                case 1:
                    viewAllCampSuggestions();
                    choice = 5;
                    break;
                case 2:
                    viewAllApprovedRejectedCampSuggestions();
                    choice = 5;
                    break;
                case 3:
                    submitNewSuggestion();
                    choice = 5;
                    break;
                case 4:
                    managePendingCampSuggestions();
                    choice = 5;
                    break;
                case 5:
                    HelperUtil.clearScreen();
                    break;
                default:
                    break;
            }
        } while (choice != 5);
    }

    public void viewAllCampSuggestions() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Camp Suggestions");

        System.out.println("  1)  View Past Camp Suggestions\n  2)  View Current Camp Suggestions\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastCampSuggestions();
                    break;
                case 2:
                    viewCurrentCampSuggestions();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        manageCampSuggestions();
    }

    public void viewPastCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Camp Suggestions");

        ArrayList<Camp> camps = CampController.getCommitteePastCamps(userId);
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        System.out.printf(" Suggestion%47s | Status   %n", "");
        for (Camp camp : camps) {
            ArrayList<Suggestion> campSuggestions = SuggestionController
                    .getSuggestionsbyCampIdAndCreatorId(camp.getCampId(), userId);
            if (campSuggestions.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campSuggestions.size(); i++)
                common.printUserSuggestionDetails(campSuggestions.get(i));
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No suggestions found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
    }

    public void viewCurrentCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Current Camp Suggestions");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Suggestion> suggestions = SuggestionController
                .getSuggestionsbyCampIdAndCreatorId(camp.getCampId(), userId);

        System.out.printf(" Suggestion%47s | Status   %n", "");
        if (suggestions.size() != 0) {
            common.printDivider(2);
            System.out.printf(" %s%n", camp.getName());
        } else {
            common.printDivider(2);
            System.out.println(" No suggestions found.\n");
        }

        for (int i = 0; i < suggestions.size(); i++)
            common.printUserSuggestionDetails(suggestions.get(i));

        HelperUtil.pressAnyKeyToContinue();
    }

    public void viewAllApprovedRejectedCampSuggestions() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("List of Approved/Rejected Camp Suggestions");

        System.out.println(
                "  1)  View Past Approved/Rejected Camp Suggestions\n  2)  View Approved/Rejected Current Camp Suggestions\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastApprovedRejectedCampSuggestions();
                    break;
                case 2:
                    viewCurrentApprovedRejectedCampSuggestions();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        manageCampSuggestions();
    }

    public void viewPastApprovedRejectedCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Approved/Rejected Camp Suggestions");

        ArrayList<Camp> camps = CampController.getCommitteePastCamps(userId);
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        System.out.printf(" Suggestion%47s | Status   %n", "");
        for (Camp camp : camps) {
            ArrayList<Suggestion> campSuggestions = SuggestionController
                    .getApprovedRejectedSuggestionsByCampIdAndCreatorId(camp.getCampId(), userId);
            if (campSuggestions.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campSuggestions.size(); i++)
                common.printUserSuggestionDetails(campSuggestions.get(i));
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No suggestions found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
    }

    public void viewCurrentApprovedRejectedCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Current Approved/Rejected Camp Suggestions");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Suggestion> suggestions = SuggestionController
                .getApprovedRejectedSuggestionsByCampIdAndCreatorId(camp.getCampId(), userId);

        System.out.printf(" Suggestion%47s | Status   %n", "");
        if (suggestions.size() != 0) {
            common.printDivider(2);
            System.out.printf(" %s%n", camp.getName());
        } else {
            common.printDivider(2);
            System.out.println(" No suggestions found.\n");
        }

        for (int i = 0; i < suggestions.size(); i++)
            common.printUserSuggestionDetails(suggestions.get(i));

        HelperUtil.pressAnyKeyToContinue();
    }

    public void submitNewSuggestion() {
        String message = "";
        HelperUtil.clearScreen();
        printMenuTitle("Submit New Suggestion");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        common.printCampDetailsWithRole(camp, 1, userId);

        do {
            System.out.print("\nEnter your suggestion message: ");
            message = HelperUtil.nextString();

            if (message.equals(""))
                System.out.println("Invalid message, please try again.");
            else {
                SuggestionController.createSuggestion(camp.getCampId(), userId, message);
                System.out.println("\nSuggestion successfully submitted.");
            }
        } while (message.equals(""));

        HelperUtil.pressAnyKeyToContinue();
        manageCampSuggestions();
    }

    public void managePendingCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("Manage Pending Suggestions");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Suggestion> suggestions = SuggestionController
                .getPendingSuggestionsByCampId(camp.getCampId());

        System.out.printf(" No. | Suggestion%28s | Status   %n", "");
        if (suggestions.size() != 0) {
            common.printDivider(2);
            System.out.printf(" %s%n", camp.getName());

            for (int i = 0; i < suggestions.size(); i++)
                common.printUserSuggestionDetailsWithIndex(suggestions.get(i), i + 1);

            int choice = -1, index;
            System.out.println("\n  1)  Edit Suggestion\n  2)  Delete Suggestion\n  3)  Back");
            do {
                System.out.print("\nEnter your choice: ");
                choice = HelperUtil.nextInt(1, 3);

                switch (choice) {
                    case 1:
                        index = -1;
                        do {
                            System.out.print("Select a Suggestion to edit: ");
                            index = HelperUtil.nextInt(1, suggestions.size());
                        } while (index == -1);

                        editSuggestion(suggestions.get(index - 1), index);
                        break;

                    case 2:
                        index = -1;
                        do {
                            System.out.print("Select a Suggestion to delete: ");
                            index = HelperUtil.nextInt(1, suggestions.size());
                        } while (index == -1);

                        deleteSuggestion(suggestions.get(index - 1), index);
                        break;

                    case 3:
                        break;
                    default:
                        break;
                }
            } while (choice == -1);
        } else {
            common.printDivider(2);
            System.out.println(" No pending suggestions found.\n");
            HelperUtil.pressAnyKeyToContinue();
        }

        HelperUtil.clearScreen();
        manageCampSuggestions();
    }

    public void editSuggestion(Suggestion suggestion, int index) {
        String message = "";
        HelperUtil.clearScreen();
        printMenuTitle("Edit Suggestion " + index);

        System.out.printf(" Suggestion%47s | Status   %n", "");
        common.printUserSuggestionDetails(suggestion);

        do {
            System.out.print("\nEnter your new suggestion message: ");
            message = HelperUtil.nextString();

            if (message.equals(""))
                System.out.println("Invalid message, please try again.");
            else {
                SuggestionController.updateSuggestionMessage(suggestion.getSuggestionId(), message);
                System.out.println("\nSuggestion successfully updated.");
            }
        } while (message.equals(""));

        HelperUtil.pressAnyKeyToContinue();
    }

    public void deleteSuggestion(Suggestion suggestion, int index) {
        String confirm = "";
        HelperUtil.clearScreen();
        printMenuTitle("Delete Suggestion " + index);

        System.out.printf(" Suggestion%47s | Status   %n", "");
        common.printUserSuggestionDetails(suggestion);

        do {
            System.out.print("\nAre you sure you want to delete this suggestion? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                SuggestionController.deleteSuggestion(suggestion.getSuggestionId());
                System.out.println("Suggestion successfully deleted.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
    }

    public void generateCampParticipantsCommitteeReport() {
        ArrayList<Camp> camps = CampController.getCommitteeCamps(userId);
        CommonUse.FilterReport(camps);

        System.out.println("\nCamp Participants/Committee Report generated successfully.");
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }
}
