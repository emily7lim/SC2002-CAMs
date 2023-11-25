package view;

import java.util.ArrayList;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Student;
import model.enums.Role;
import utils.HelperUtil;

/**
 * User Interface for Students
 * 
 * @author Emily, Chloie
 * @version 2.2.3
 * @since 2023-11-04
 */
public class StudentView extends MainView {
    /**
     * Student Menu Title
     */
    private final String MENU_TITLE = "Student Menu";

    /**
     * User ID of logged in User
     */
    protected String userId;
    /**
     * CommonUse object for generic view functions
     */
    protected CommonUse common;
    /**
     * FilterCampsView object for Filter Camps Menu
     */
    protected FilterCampsView filterCampsView;

    /**
     * Default constructor
     */
    public StudentView() {
        common = new CommonUse();
        filterCampsView = new FilterCampsView();
    }

    /**
     * Sets the logged in User's ID
     * 
     * @param userId The User ID of the logged in Student
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Menu for the Student Menu interface
     */
    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View Available Camps");
        System.out.println("  2)  View Registered Camps");
        System.out.println("  3)  Register for Camp");
        System.out.println("  4)  Withdraw from Camp");
        System.out.println("  5)  Manage Enquiries");
        System.out.println("  6)  Profile");
        System.out.println("  7)  Change Password");
        System.out.println("  8)  Logout");
    }

    /**
     * Application for the Student Menu
     */
    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 8);

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
                    viewProfile();
                    printMenu();
                    break;
                case 7:
                    changePassword();
                    printMenu();
                    break;
                case 8:
                    break;
                default:
                    break;
            }
        } while (choice != 8);
    }

    /**
     * Menu for Camps available to Student
     */
    public void viewAvailableCamps() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View Available Camps");
        System.out.println("  1)  View Available Camps\n  2)  Filter Available Camps\n  3)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewStudentAvailableCamps();
                    break;
                case 2:
                    viewFilteredAvailableCamps();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        if (choice != 3)
            HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of all camps available to the Student
     */
    public void viewStudentAvailableCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Available Camps");

        ArrayList<Camp> camps = CampController
                .getStudentAvailableCamps(UserController.getUserByUserId(userId).getFaculty());

        if (camps.size() == 0)
            System.out.printf(" No camps found.\n\n", "", "");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetailsWithRole(camps.get(i), i + 1, userId);
    }

    /**
     * Menu for filtering the Camps available to the Student
     */
    public void viewFilteredAvailableCamps() {
        filterCampsView.filterCamps("Filter Available Camps", common, CampController
                .getStudentAvailableCamps(UserController.getUserByUserId(userId).getFaculty()));
    }

    /**
     * Menu for the Student's registered Camps
     */
    public void viewRegisteredCamps() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View Registered Camps");
        System.out.println(
                "  1)  View Past Registered Camps\n  2)  View Future Registered Camps\n  3)  Filter Registered Camps\n  4)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastRegisteredCamps();
                    break;
                case 2:
                    viewFutureRegisteredCamps();
                    break;
                case 3:
                    viewFilteredRegisteredCamps();
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        if (choice != 4)
            HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of the Student's past registered Camps
     */
    public void viewPastRegisteredCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Registered Camps");
        ArrayList<Camp> camps = CampController.getStudentPastRegisteredCamps(userId);
        camps.addAll(CampController.getParticipantWithdrawnCamps(userId));

        if (camps.size() == 0)
            System.out.printf(" No past registered camps found.\n\n", "", "");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetailsWithRole(camps.get(i), i + 1, userId);
    }

    /**
     * Prints a list of the Student's current/future registered Camps
     */
    public void viewFutureRegisteredCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Future Registered Camps");
        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);

        if (camps.size() == 0)
            System.out.printf(" No future registered camps found.\n\n", "", "");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetailsWithRole(camps.get(i), i + 1, userId);
    }

    /**
     * Menu for filtering a Student's registered Camps
     */
    public void viewFilteredRegisteredCamps() {
        filterCampsView.filterCamps("Filter Registered Camps", common, CampController.getCommitteeCamps(userId));
    }

    /**
     * Menu for selecting Camp for registration
     */
    public void registerForCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Register for Camps");

        ArrayList<Camp> camps = CampController
                .getStudentAvailableUnregisteredCamps(UserController.getUserByUserId(userId).getFaculty(), userId);

        if (camps.size() == 0)
            System.out.printf(" No camps found.\n\n", "", "");
        else {
            for (int i = 0; i < camps.size(); i++)
                common.printCampDetails(camps.get(i), i + 1);

            do {
                System.out.print("\nSelect a Camp to register: ");
                index = HelperUtil.nextInt(1, camps.size());
            } while (index == -1);

            registerForCamp(camps.get(index - 1), index);
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for Camp registration
     * 
     * @param camp  The Camp that the Student selected for registration
     * @param index The index of the Camp in the camps list
     */
    public void registerForCamp(Camp camp, int index) {
        int choice = -1;
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Register for Camp " + index);

        common.printCampDetails(camp, index);

        System.out.println("\nSelect a Role:\n  1)  Camp Committee\n  2)  Camp Participant");
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 2);

            switch (choice) {
                case 1:
                    if (camp.getRemainingCommitteeSlots() == 0)
                        System.out.println("Unable to register as a committee, no remaining slots left.\n\n");
                    else if (CampController.checkCurrentCampCommittee(userId))
                        System.out.println(
                                "Unable to register as a committee, you are already a committee for another camp.\n\n");
                    else if (CampController.checkCampParticipant(userId, camp.getStartDate(), camp.getEndDate()))
                        System.out.println(
                                "Unable to register as a committee, you are already registered for another overlapping camp.\n\n");
                    else {
                        do {
                            System.out.print("\nAre you sure you want to register for this camp? (y/n) ");
                            confirm = HelperUtil.nextString().toLowerCase();

                            if (confirm.equals("y")) {
                                CampController.addCommittee(camp.getCampId(), userId);
                                StudentController.addCampCommittee(userId);
                                System.out.println(
                                        "Successfully registered as Camp Committee, \nplease log in again to access committee menu.\n");
                                break;
                            } else if (!confirm.equals("n"))
                                System.out.println("Invalid input, please try again.");
                            else
                                break;
                        } while (true);
                    }
                    break;

                case 2:
                    if (camp.getRemainingParticipantSlots() == 0)
                        System.out.println("Unable to register as a participant, no remaining slots left.\n\n");
                    else if (CampController.checkCampParticipant(userId, camp.getStartDate(), camp.getEndDate()))
                        System.out.println(
                                "Unable to register as a participant, you are already registered for another overlapping camp.\n\n");
                    else if (CampController.checkCampParticipantWithdrawn(camp.getCampId(), userId))
                        System.out.println(
                                "Unable to register as participant, you have previously withdrawn from this Camp.\n\n");
                    else {
                        do {
                            System.out.print("\nAre you sure you want to register for this camp? (y/n) ");
                            confirm = HelperUtil.nextString().toLowerCase();

                            if (confirm.equals("y")) {
                                CampController.addParticipant(camp.getCampId(), userId);
                                System.out.println("Successfully registered as Camp Participant.\n");
                                break;
                            } else if (!confirm.equals("n"))
                                System.out.println("Invalid input, please try again.");
                            else
                                break;
                        } while (true);
                    }
                    break;
                default:
                    break;
            }
        } while (choice == -1);
    }

    /**
     * Menu for selecting Camp for withdrawal
     */
    public void withdrawFromCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Withdraw from Camps");

        ArrayList<Camp> camps = CampController.getParticipantFutureRegisteredCamps(userId);

        if (camps.size() == 0)
            System.out.printf(" No registered camps found.\n\n", "", "");
        else {
            for (int i = 0; i < camps.size(); i++)
                common.printCampDetails(camps.get(i), i + 1);

            do {
                System.out.print("\nSelect a Camp to withdraw from: ");
                index = HelperUtil.nextInt(1, camps.size());
            } while (index == -1);

            withdrawFromCamp(camps.get(index - 1), index);
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for Camp withdrawal
     * 
     * @param camp  The Camp that the Student selected for withdrawal
     * @param index The index of the Camp in the camps list
     */
    public void withdrawFromCamp(Camp camp, int index) {
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Withdraw from Camp " + index);

        common.printCampDetails(camp, index);

        do {
            System.out.print("\nAre you sure you want to withdraw from this camp? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                CampController.removeParticipant(camp.getCampId(), userId);
                System.out.println("Successfully withdrawn from Camp.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);
    }

    /**
     * Menu for managing Enquiries
     */
    public void manageEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Manage Enquiries");
        System.out.println(
                "  1)  View All Enquiries\n  2)  View Enquiry Replies\n  3)  Submit New Enquiry\n  4)  Manage Pending Enquiries\n  5)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 5);

            switch (choice) {
                case 1:
                    viewAllEnquiries();
                    choice = 5;
                    break;

                case 2:
                    viewEnquiryReplies();
                    choice = 5;
                    break;

                case 3:
                    submitNewEnquiry();
                    choice = 5;
                    break;

                case 4:
                    managePendingEnquiries();
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
     * Menu for the Student's Camp Enquiries
     */
    public void viewAllEnquiries() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View All Enquiries");
        System.out.println("  1)  View Past Camps Enquiries\n  2)  View Future Camps Enquiries\n  3)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastCampsEnquiries();
                    break;

                case 2:
                    viewFutureCampsEnquiries();
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        } while (choice == -1);

        manageEnquiries();
    }

    /**
     * Prints a list of the Student's Enquiries for past Camps
     */
    public void viewPastCampsEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Camps Enquiries");

        ArrayList<Camp> camps = CampController.getStudentPastRegisteredCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%51s | Status  %n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getEnquiriesByCreatorIdAndCampId(userId,
                    camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printUserEnquiryDetails(campEnquiries.get(i));
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No enquiries found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of the Student's Enquiries for current/future Camps
     */
    public void viewFutureCampsEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Future Camps Enquiries");

        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%51s | Status  %n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getEnquiriesByCreatorIdAndCampId(userId,
                    camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printUserEnquiryDetails(campEnquiries.get(i));
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No enquiries found.\n");
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for the Student's Camp Enquiries with replies
     */
    public void viewEnquiryReplies() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View Enquiry Replies");
        System.out
                .println("  1)  View Past Camps Enquiry Replies\n  2)  View Future Camps Enquiry Replies\n  3)  Back");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    viewPastCampsEnquiryReplies();
                    break;

                case 2:
                    viewFutureCampsEnquiryReplies();
                    break;

                case 3:
                    break;

                default:
                    break;
            }
        } while (choice == -1);

        manageEnquiries();
    }

    /**
     * Prints a list of the Student's Enquiries with replies for past Camps
     */
    public void viewPastCampsEnquiryReplies() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Past Camps Enquiry Replies");

        ArrayList<Camp> camps = CampController.getStudentPastRegisteredCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%20s | Response%n", "", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getRepliedEnquiriesByCreatorIdAndCampId(userId,
                    camp.getCampId());
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
        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of the Student's Enquiries with replies for current/future
     * Camps
     */
    public void viewFutureCampsEnquiryReplies() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Future Camps Enquiry Replies");

        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" Enquiry%20s | Response%n", "", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getRepliedEnquiriesByCreatorIdAndCampId(userId,
                    camp.getCampId());
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
        HelperUtil.clearScreen();
    }

    /**
     * Menu for selecting Camp for new Enquiry
     */
    public void submitNewEnquiry() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Submit New Enquiry");
        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);
        camps.remove(CampController.getCommitteeCurrentCamp(userId));

        if (camps.size() == 0)
            System.out.printf(" No registered camps found.\n\n", "", "");
        else {
            for (int i = 0; i < camps.size(); i++)
                common.printCampDetailsWithRole(camps.get(i), i + 1, userId);

            do {
                System.out.print("\nSelect a Camp to enquire: ");
                index = HelperUtil.nextInt(1, camps.size());
            } while (index == -1);

            submitEnquiry(camps.get(index - 1), index);
        }

        HelperUtil.pressAnyKeyToContinue();
        manageEnquiries();
    }

    /**
     * Menu for submitting new Camp Enquiry
     * 
     * @param camp  The Camp that the Student selected for a new Enquiry
     * @param index The index of the Camp in the camps list
     */
    public void submitEnquiry(Camp camp, int index) {
        String message = "";
        HelperUtil.clearScreen();
        printMenuTitle("Submit New Enquiry for Camp " + index);

        common.printCampDetailsWithRole(camp, index, userId);

        do {
            System.out.print("\nEnter your enquiry message: ");
            message = HelperUtil.nextString();

            if (message.equals(""))
                System.out.println("Invalid message, please try again.");
            else {
                String enquiryId = EnquiryController.createEnquiry(camp.getCampId(), userId, message);
                CampController.addEnquiry(camp.getCampId(), enquiryId);
                System.out.println("\nEnquiry successfully submitted.");
            }
        } while (message.equals(""));
    }

    /**
     * Menu for managing pending Enquiries
     */
    public void managePendingEnquiries() {
        HelperUtil.clearScreen();
        printMenuTitle("Manage Pending Enquiries");

        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);
        ArrayList<Enquiry> enquiries = new ArrayList<>();

        System.out.printf(" No. | Enquiry%45s | Status  %n", "");
        for (Camp camp : camps) {
            ArrayList<Enquiry> campEnquiries = EnquiryController.getPendingEnquiriesByCreatorIdAndCampId(userId,
                    camp.getCampId());
            if (campEnquiries.size() != 0) {
                common.printDivider(2);
                System.out.printf(" %s%n", camp.getName());
            }
            for (int i = 0; i < campEnquiries.size(); i++)
                common.printUserEnquiryDetailsWithIndex(campEnquiries.get(i), i + 1);
            enquiries.addAll(campEnquiries);
        }

        if (enquiries.size() == 0) {
            common.printDivider(2);
            System.out.println(" No pending enquiries found.\n");
            HelperUtil.pressAnyKeyToContinue();
        } else {
            int choice = -1, index;
            System.out.println("\n  1)  Edit Enquiry\n  2)  Delete Enquiry\n  3)  Back");
            do {
                System.out.print("\nEnter your choice: ");
                choice = HelperUtil.nextInt(1, 3);

                switch (choice) {
                    case 1:
                        index = -1;
                        do {
                            System.out.print("Select an Enquiry to edit: ");
                            index = HelperUtil.nextInt(1, enquiries.size());
                        } while (index == -1);

                        editEnquiry(enquiries.get(index - 1), index);
                        break;

                    case 2:
                        index = -1;
                        do {
                            System.out.print("Select an Enquiry to delete: ");
                            index = HelperUtil.nextInt(1, enquiries.size());
                        } while (index == -1);

                        deleteEnquiry(enquiries.get(index - 1), index);
                        break;

                    case 3:
                        break;

                    default:
                        break;
                }
            } while (choice == -1);
        }

        HelperUtil.clearScreen();
        manageEnquiries();
    }

    /**
     * Menu for editing pending Enquiry
     * 
     * @param enquiry The Enquiry that the Student selected for edits
     * @param index   The index of the Enquiry in the enquiries list
     */
    public void editEnquiry(Enquiry enquiry, int index) {
        String message = "";
        HelperUtil.clearScreen();
        printMenuTitle("Edit Enquiry " + index);

        System.out.printf(" Enquiry%51s | Status  %n", "");
        common.printUserEnquiryDetails(enquiry);

        do {
            System.out.print("\nEnter your new enquiry message: ");
            message = HelperUtil.nextString();

            if (message.equals(""))
                System.out.println("Invalid message, please try again.");
            else {
                EnquiryController.updateEnquiryMessage(enquiry.getEnquiryId(), message);
                System.out.println("\nEnquiry successfully updated.");
            }
        } while (message.equals(""));

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Menu for deleting pending Enquiry
     * 
     * @param enquiry The Enquiry that the Student selected for deletion
     * @param index   The index of the Enquiry in the enquiries list
     */
    public void deleteEnquiry(Enquiry enquiry, int index) {
        String confirm = "";
        HelperUtil.clearScreen();
        printMenuTitle("Delete Enquiry " + index);

        System.out.printf(" Enquiry%51s | Status  %n", "");
        common.printUserEnquiryDetails(enquiry);

        do {
            System.out.print("\nAre you sure you want to delete this enquiry? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                CampController.removeEnquiry(enquiry.getCampId(), enquiry.getEnquiryId());
                EnquiryController.deleteEnquiry(enquiry.getEnquiryId());
                System.out.println("Enquiry successfully deleted.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
    }

    /**
     * Prints the profile of the Student
     */
    public void viewProfile() {
        Student user = StudentController.getStudentByUserId(userId);
        HelperUtil.clearScreen();
        printMenuTitle(user.getName() + "'s Profile");

        System.out.printf("User ID:  %s%n", user.getUserId());
        System.out.printf("Name:\t  %s%n", user.getName());
        System.out.printf("Faculty:  %s%n", user.getFaculty().getFaculty());
        System.out.printf("Points:\t  %d%n", user.getPoints());
        System.out.printf("Role:\t  %s%s%n\n", user.getRole().getRole(),
                user.getRole() == Role.COMMITTEE ? " (" + CampController.getCommitteeCurrentCamp(userId).getName() + ")"
                        : "");

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

                do {
                    System.out.print("Enter your new password: ");
                    newPassword = HelperUtil.validatePassword(HelperUtil.nextString());
                } while (newPassword.equals(""));

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