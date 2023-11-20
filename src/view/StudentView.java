package view;

import java.util.ArrayList;

import controller.*;
import model.Camp;
import model.Enquiry;
import model.Student;
import model.enums.Role;
import utils.HelperUtil;

public class StudentView extends MainView {
    private final String MENU_TITLE = "Student Menu";

    protected String userId;
    protected CommonUse common;

    public StudentView() {
        common = new CommonUse();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View All Camps");
        System.out.println("  2)  View Registered Camps");
        System.out.println("  3)  Register for Camp");
        System.out.println("  4)  Withdraw from Camp");
        System.out.println("  5)  Manage Enquiries");
        System.out.println("  6)  Profile");
        System.out.println("  7)  Change Password");
        System.out.println("  8)  Logout");
    }

    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 8);

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

    public void viewAllCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Camps");

        ArrayList<Camp> camps = CampController.getStudentAvailableCamps(UserController.getUserByUserId(userId).getFaculty());

        if (camps.size() == 0)
            System.out.printf(" No camps found.\n\n", "", "");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetailsWithRole(camps.get(i), i + 1, userId);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void viewRegisteredCamps() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("View Registered Camps");
        System.out.println("  1)  View Past Registered Camps\n  2)  View Future Registered Camps\n  3)  Back");

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
                    break;
                default:
                    break;
            }
        } while (choice == -1);

        if (choice != 3)
            HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

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

    public void viewFutureRegisteredCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Future Registered Camps");
        ArrayList<Camp> camps = CampController.getStudentFutureRegisteredCamps(userId);

        if (camps.size() == 0)
            System.out.printf(" No future registered camps found.\n\n", "", "");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetailsWithRole(camps.get(i), i + 1, userId);
    }

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
                                System.out.println("Successfully registered as Camp Committee, \nplease log in again to access committee menu.\n");
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

    // TODO: Add Enquiry ID to Enquiry List of Camp
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
                EnquiryController.createEnquiry(camp.getCampId(), userId, message);
                System.out.println("\nEnquiry successfully submitted.");
            }
        } while (message.equals(""));
    }

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

    public void viewProfile() {
        Student user = StudentController.getStudentByUserId(userId);
        HelperUtil.clearScreen();
        printMenuTitle(user.getName() + "'s Profile");

        System.out.printf("User ID:  %s%n", user.getUserId());
        System.out.printf("Name:\t  %s%n", user.getName());
        System.out.printf("Faculty:  %s%n", user.getFaculty().getFaculty());
        System.out.printf("Points:\t  %d%n", user.getPoints());
        System.out.printf("Role:\t  %s%s%n\n", user.getRole().getRole(), user.getRole() == Role.COMMITTEE ? " (" + CampController.getCommitteeCurrentCamp(userId).getName() +")" : "");

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
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