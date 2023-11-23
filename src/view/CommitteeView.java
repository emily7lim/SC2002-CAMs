package view;

import java.util.ArrayList;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Suggestion;
import report.enums.ReportType;
import utils.HelperUtil;

/**
 * User Interface for Camp Committees
 * 
 * @author Emily, Chloie
 * @version 2.4.0
 * @since 2023-11-04
 */
public class CommitteeView extends StudentView {
    private final String MENU_TITLE = "Committee Menu";

    /**
     * Default constructor
     */
    public CommitteeView() {
        super();
    }

    /**
     * Menu for the Committee Menu Interface
     */
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

    /**
     * Application for the Committee Menu
     */
    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 11);

            switch (choice) {
                case 1:
                    viewAvailableCamps();
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

    /**
     * Menu for managing Student Camp Enquiries
     */
    public void manageStudentCampEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Student Camp Enquiries");

        System.out.println(
                "  1)  View All Student Camp Enquiries\n  2)  View All Replied Student Camp Enquiries\n  3)  Manage Pending Student Camp Enquiries\n  4)  Generate Camp Enquiry Report\n  5)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 5);

            switch (choice) {
                case 1:
                    viewAllStudentCampEnquiries();
                    break;
                case 2:
                    viewAllStudentRepliedCampEnquiries();
                    break;
                case 3:
                    managePendingStudentCampEnquiries();
                    break;
                case 4:
                    generateCampEnquiriesReport();
                    break;
                case 5:
                    HelperUtil.clearScreen();
                    break;
                default:
                    break;
            }
        } while (choice == -1);
    }

    /**
     * Menu for the Committee's Camp Enquiries from Students
     */
    public void viewAllStudentCampEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Student Camp Enquiries");

        System.out.println(
                "  1)  View Past Student Camp Enquiries\n  2)  View Current Student Camp Enquiries\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastStudentCampEnquiries();
                    break;

                case 2:
                    viewCurrentStudentCampEnquiries();
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.clearScreen();
        manageStudentCampEnquiries();
    }

    /**
     * Prints a list of the Committee's past Camp Enquiries from Students
     */
    public void viewPastStudentCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Student Camp Enquiries");

        ArrayList<Camp> camps = CampController.getCommitteePastCamps(userId);
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
            System.out.println(" No enquiries found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Prints a list of the Committee's current Camp Enquiries from Students
     */
    public void viewCurrentStudentCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Current Student Camp Enquiries");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Enquiry> enquiries = EnquiryController.getEnquiriesByCampId(camp.getCampId());

        System.out.printf(" Enquiry%38s | Status  | Creator%n", "");
        common.printDivider(2);

        if (enquiries.size() != 0)
            System.out.printf(" %s%n", camp.getName());
        else
            System.out.println(" No enquiries found.\n");

        for (int i = 0; i < enquiries.size(); i++)
            common.printEnquiryDetails(enquiries.get(i));

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Menu for the Committee's Camp Enquiries with replies from Students
     */
    public void viewAllStudentRepliedCampEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Student Replied Camp Enquiries");

        System.out.println(
                "  1)  View Past Student Replied Camp Enquiries\n  2)  View Current Student Replied Camp Enquiries\n  3)  Back");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastStudentRepliedCampEnquiries();
                    break;

                case 2:
                    viewCurrentStudentRepliedCampEnquiries();
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.clearScreen();
        manageStudentCampEnquiries();
    }

    /**
     * Prints a list of the Committee's past Camp Enquiries with replies from Students
     */
    public void viewPastStudentRepliedCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Student Replied Camp Enquiries");

        ArrayList<Camp> camps = CampController.getCommitteePastCamps(userId);
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
            System.out.println(" No enquiries found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Prints a list of the Committee's current Camp Enquiries with replies from Students
     */
    public void viewCurrentStudentRepliedCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Current Student Replied Camp Enquiries");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Enquiry> enquiries = EnquiryController.getRepliedEnquiriesByCampId(camp.getCampId());

        System.out.printf(" Enquiry%20s | Response%n", "", "");
        common.printDivider(2);

        if (enquiries.size() != 0)
            System.out.printf(" %s%n", camp.getName());
        else
            System.out.println(" No enquiries found.\n");

        for (int i = 0; i < enquiries.size(); i++)
            common.printEnquiryDetailsWithReply(enquiries.get(i));

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Menu for managing the Committee's current pending Camp Enquiries from Students
     */
    public void managePendingStudentCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Student Camp Enquiries");

        Camp camp = CampController.getCommitteeCurrentCamp(userId);
        ArrayList<Enquiry> enquiries = EnquiryController.getPendingEnquiriesByCampId(camp.getCampId());

        System.out.printf(" No. | Enquiry%32s | Status  | Creator%n", "");
        if (enquiries.size() != 0) {
            common.printDivider(2);
            System.out.printf(" %s%n", camp.getName());
        } else {
            common.printDivider(2);
            System.out.println(" No pending suggestions found.\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            manageStudentCampEnquiries();
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

                    replyStudentCampEnquiry(enquiries.get(index - 1), index);
                    break;

                case 2:
                    HelperUtil.clearScreen();
                    manageStudentCampEnquiries();
                    break;
                default:
                    break;
            }
        } while (choice == -1);
    }

    /** 
     * Menu for replying an Enquiry
     * @param enquiry The Enquiry that the Committee selected to reply
     * @param index The index of the Enquiry in the enquiries list
     */
    public void replyStudentCampEnquiry(Enquiry enquiry, int index) {
        String reply = "";
        HelperUtil.clearScreen();
        printMenuTitle("Reply Camp Enquiry " + index);

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
        managePendingStudentCampEnquiries();
    }

    /**
     * Calls a method to generate a report of all Enquiries of the Committee's Camps
     */
    public void generateCampEnquiriesReport() {
        ArrayList<Camp> camps = CampController.getCommitteeCamps(userId);
        CommonUse.FileType(camps, null, ReportType.ENQUIRIES_REPORT);

        System.out.println("\nCamp Enquiries Report generated successfully.");

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
        manageStudentCampEnquiries();
    }

    /**
     * Menu to manage the Committee's Camp Suggestion
     */
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

    /**
     * Menu for the Committee's Camp Suggestions
     */
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

    /**
     * Prints a list of the Committee's past Camp Suggestions
     */
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

    /**
     * Prints a list of the Committee's current Camp Suggestions
     */
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

    /**
     * Menu for the Committee's approved/rejected Camp Suggestions
     */
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

    /**
     * Prints a list of the Committee's approved/rejected past Camp Suggestions
     */
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

    /**
     * Prints a list of the Committee's approved/rejected current Camp Suggestions
     */
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

    /**
     * Menu for submitting a new Suggestion
     */
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
                String suggestionId = SuggestionController.createSuggestion(camp.getCampId(), userId, message);
                CampController.addSuggestion(camp.getCampId(), suggestionId);
                System.out.println("\nSuggestion successfully submitted.");
            }
        } while (message.equals(""));

        HelperUtil.pressAnyKeyToContinue();
        manageCampSuggestions();
    }

    /**
     * Menu for managing the Committee's pending Camp Suggestions
     */
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

    /**
     * Menu for editing a pending Camp Suggestion
     * @param suggestion The Suggestion that the Committee selected for editing
     * @param index The index of the Suggestion in the suggestions list
     */
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

    /**
     * Menu for deleting a pending Camp Suggestion
     * @param suggestion The Suggestion that the Committee selected for editing
     * @param index The index of the Suggestion in the suggestions list
     */
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
                CampController.removeSuggestion(suggestion.getCampId(), suggestion.getSuggestionId());
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

    /**
     * Calls a method to generate a report of all the Camp Participants/Committee of
     * the Committee's Camps
     */
    public void generateCampParticipantsCommitteeReport() {
        ArrayList<Camp> camps = CampController.getCommitteeCamps(userId);
        CommonUse.FilterReport(camps);

        System.out.println("\nCamp Participants/Committee Report generated successfully.");
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }
}
