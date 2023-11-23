package view;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Staff;
import model.Suggestion;
import report.enums.ReportType;
import utils.ForDate;
import utils.HelperUtil;

import java.util.*;

/**
 * User Interface for Staff
 * 
 * @author Emily, Chloie
 * @version 2.2.2
 * @since 2023-11-02
 */
public class StaffView extends MainView {
    private final String MENU_TITLE = "Staff Menu";

    private String userId;
    private CommonUse common;
    private CreatedCampsView createdCampsView;

    /**
     * Default constructor
     */
    public StaffView() {
        common = new CommonUse();
        createdCampsView = new CreatedCampsView();
    }

    /**
     * Sets the logged in User's ID
     * 
     * @param userId The User ID of the logged in Staff
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Menu for the Staff Menu interface
     */
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

    /**
     * Application for the Staff Menu
     */
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
                    viewProfile();
                    printMenu();
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

    /**
     * Menu for all Camp or Camp Participants
     */
    public void viewAllCampsParticipantsMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Camps/Participants");
        System.out.println(
                "  1)  View All Camp Details\n  2)  View All Camp Participants/Committee\n  3)  Filter View\n  4)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 4);

            switch (choice) {
                case 1:
                    viewAllCampDetails();
                    break;
                case 2:
                    viewAllCampParticipants();
                    break;
                case 3:
                    viewFilteredCamp();
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

    /**
     * Prints a list of all the Camps
     */
    public void viewAllCampDetails() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Camps");

        ArrayList<Camp> campList = CampController.getAllCamps();
        for (int i = 0; i < campList.size(); i++)
            common.printCampDetails(campList.get(i), i + 1);
    }

    /**
     * Prints a list of all Camp participants
     */
    public void viewAllCampParticipants() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Camp Participants/Committee");

        ArrayList<Camp> campList = CampController.getAllCamps();
        for (int i = 0; i < campList.size(); i++)
            common.printCampParticipants(campList.get(i), i + 1);
    }

    /**
     * Prints a list of filtered camps
     */
    public void viewFilteredCamp() {
        HelperUtil.clearScreen();
        printMenuTitle("Filtered camps");
        int choice = -1, totalSlots = -1, commSlots = -1;

        ArrayList<Camp> camp = CampController.getAllCamps();
        if (camp.size() == 0) {
            System.out.println(" No camps found.\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            return;
        }

        System.out.println(
                "\nSelect a field to filter\n  1)  Name\n  2)  Location\n  3)  Start Date\n  4)  End Date\n  5)  Registration Close Date\n  6)  User Group\n  7)  Total Slots\n  8)  Committee Slots");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 8);

            switch (choice) {
                case 1:
                    String name = "";

                    System.out.print("Enter Camp Name: ");
                    name = HelperUtil.nextString();
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampName(name))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 2:
                    String location = "";

                    System.out.print("Enter Camp Location: ");
                    location = HelperUtil.nextString();
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampLocation(location))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 3:
                    Date startDate = null;
                    do {
                        System.out.print("Enter Camp Start Date (dd/MM/yyy): ");
                        startDate = ForDate.getDates(HelperUtil.nextString());
                        if (startDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (startDate == null);
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampStart(startDate))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 4:
                    Date endDate = null;
                    do {
                        System.out.print("Enter Camp End Date (dd/MM/yyy): ");
                        endDate = ForDate.getDates(HelperUtil.nextString());
                        if (endDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (endDate == null);
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampEnd(endDate))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 5:
                    Date registrationCloseDate = null;
                    do {
                        System.out.print("Enter Camp Registration Close Date (dd/MM/yyy): ");
                        registrationCloseDate = ForDate.getDates(HelperUtil.nextString());
                        if (registrationCloseDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (registrationCloseDate == null);

                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampDeadline(registrationCloseDate))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 6:
                    System.out.println("Enter Faculty");
                    String faculty = HelperUtil.nextString();
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampFaculty(faculty))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 7:
                    do {
                        System.out.print("Enter Camp Total Slots: ");
                        totalSlots = HelperUtil.nextInt(1);
                    } while (totalSlots == -1);
                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampTotalSlots(totalSlots))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;

                case 8:

                    do {
                        System.out.print("Enter Committee Slots: ");
                        totalSlots = HelperUtil.nextInt(1);
                    } while (totalSlots == -1);

                    for (int i = 0; i < camp.size(); i++) {

                        if (CampController.filterCampCommSlot(commSlots))
                            common.printCampDetails(camp.get(i), i + 1);
                    }
                    System.out.println("\nEnd of list of filtered camp details\n");
                    break;
                default:
                    break;
            }
        } while (choice == -1);

    }

    /**
     * Menu for managing Enquiries of the Staff's created Camps
     */
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
                    generateCampEnquiriesOrSuggestionReport(ReportType.ENQUIRIES_REPORT);
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

    /**
     * Prints a list of all Enquiries of the Staff's created Camps
     */
    public void viewAllCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Enquiries");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
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
    }

    /**
     * Prints a list of all replied Enquiries of the Staff's created Camps
     */
    public void viewAllRepliedCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Replied Enquiries");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
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
    }

    /**
     * Menu for managing the pending Enquiries of the Staff's created Camps
     */
    public void managePendingCampEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Enquiries");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" No. | Enquiry%32s | Status  | Creator%n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getPendingEnquiriesByCampId(camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printEnquiryDetailsWithIndex(campEnquiries.get(i), i + 1);
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No pending enquiries found.\n");
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

    /**
     * Menu for replying an Enquiry
     * 
     * @param enquiry The Enquiry that the Staff selected to reply
     * @param index   The index of the Enquiry in the enquiries list
     */
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

    /**
     * Calls a method to generate a report of all Enquiries or Suggestion of the Staff's created
     * Camps
     */
    public void generateCampEnquiriesOrSuggestionReport(ReportType reportType) {
        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        switch (reportType) {
            case ENQUIRIES_REPORT:
                CommonUse.FileType(camps, null, ReportType.ENQUIRIES_REPORT);
                break;
            case SUGGESTION_REPORT:
                CommonUse.FileType(camps, null, ReportType.SUGGESTION_REPORT);
                break;
            default:
                // add in more as needed
                return;
        }
        System.out.println("\n" + reportType.getReportTypeName() + " generated successfully.");
    }

    // FOR REPORT
    /**
     * Prints a list of all Camp participants
     */
    public boolean viewFilteredCampParticipants() {
        HelperUtil.clearScreen();
        ArrayList<Camp> camp = CampController.getAllCamps();

        if (camp.size() == 0) {
            System.out.println(" No camps found.\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            return false;
        }
        String name = "";
        System.out.println(name);
        System.out.print("Enter Participant Name: ");
        name = HelperUtil.nextString();
        for (int i = 0; i < camp.size(); i++) {

            if (CampController.filterAttendee(name))
                return true;
        }
        return false;
    }

    /**
     * Calls a method to generate a report of all the Camp Participants/Committee of
     * the Staff's created Camps
     */
    public void generateCampParticipantsCommitteeReport() {
        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        if (camps.size() == 0) {
            System.out.println(" No camps found :(\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            return;
        }

        CommonUse.FilterReport(camps);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Calls a method to generate a report of the performance of the Camp Committee
     * of the Staff's created Camps
     */
    public void generateCampCommitteePerformanceReport() {
        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        CommonUse.FileType(camps, null, ReportType.PERFORMANCE_REPORT);

        System.out.println("\nCamp Committee Performance Report generated successfully.");
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for managing the Suggestions of the Staff's created Camps
     */
    public void manageCampSuggestions() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Camp Suggestions");
        System.out.println(
                "  1)  View All Camp Suggestions\n  2)  View All Approved/Rejected Camp Suggestions\n  3)  Manage Pending Camp Suggestions\n  4)  Generate Camp Suggestion Report\n  5)  Back");

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
                    generateCampEnquiriesOrSuggestionReport(ReportType.SUGGESTION_REPORT);
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

    /**
     * Prints a list of all Suggestions of the Staff's created Camps
     */
    public void viewAllCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Suggestions");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
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
            System.out.println(" No suggestions found.\n");
        }
    }

    /**
     * Prints a list of all approved/rejected Suggestions of the Staff's created
     * Camps
     */
    public void viewAllApprovedRejectedCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Approved/Rejected Suggestions");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
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
            System.out.println(" No suggestions found.\n");
        }
    }

    /**
     * Menu for managing the pending Suggestions of the Staff's created Camps
     */
    public void managePendingCampSuggestions() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Pending Suggestions");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
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
                common.printSuggestionDetailsWithIndex(campSuggestions.get(i), i + 1);
            suggestions.addAll(campSuggestions);
        }

        if (suggestions.size() == 0) {
            common.printDivider(2);
            System.out.println(" No pending suggestions found.\n");
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

    /**
     * Menu for approving a Suggestion
     * 
     * @param suggestion The Suggestion that the Staff selected to approve
     * @param index      The index of the Suggesetion in the suggestions list
     */
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

    /**
     * Menu for rejecting a Suggestion
     * 
     * @param suggestion The Suggestion that the Staff selected to reject
     * @param index      The index of the Suggestion in the suggestions list
     */
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

    /**
     * Prints the profile of the Staff
     */
    public void viewProfile() {
        Staff user = StaffController.getStaffByUserId(userId);
        HelperUtil.clearScreen();
        printMenuTitle(user.getName() + "'s Profile");

        System.out.printf("User ID:  %s%n", user.getUserId());
        System.out.printf("Name:\t  %s%n", user.getName());
        System.out.printf("Faculty:  %s%n", user.getFaculty().getFaculty());
        System.out.printf("Role:\t  %s%n", user.getRole().getRole());
        System.out.printf("Created %d Camps!%n\n", user.getCreatedCampIds().size());

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for changing password
     */
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