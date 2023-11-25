package view;

import java.util.*;

import controller.*;
import model.Camp;
import model.enums.Faculty;
import utils.ForDate;
import utils.HelperUtil;

/**
 * User Interface for managing Staff's Created Camps
 * 
 * @author Emily, Chloie
 * @version 2.2.4
 * @since 2023-11-02
 */
public class CreatedCampsView extends MainView {
    /**
     * Created Camps Menu Title
     */
    private final String MENU_TITLE = "Manage Created Camps";

    /**
     * User ID of the logged in User
     */
    private String userId;
    /**
     * CommonUse object for generic view functions
     */
    private CommonUse common;
    /**
     * FilterCampsView object for Filter Camps Menu
     */
    private FilterCampsView filterCampsView;

    /**
     * Default constructor
     */
    public CreatedCampsView() {
        common = new CommonUse();
        filterCampsView = new FilterCampsView();
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
     * Menu for managing Staff's Created Camps
     */
    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View All Created Camps");
        System.out.println("  2)  View Created Camps Participants/Committee");
        System.out.println("  3)  Filter Created Camps");
        System.out.println("  4)  Create New Camp");
        System.out.println("  5)  Edit Created Camp");
        System.out.println("  6)  Delete Created Camp");
        System.out.println("  7)  Back");
    }

    /**
     * Application for managing Staff's Created Camps
     */
    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 7);

            switch (choice) {
                case 1:
                    viewAllCreatedCamps();
                    printMenu();
                    break;
                case 2:
                    viewCreatedCampParticipants();
                    printMenu();
                    break;
                case 3:
                    viewFilteredCreatedCamps();
                    printMenu();
                    break;
                case 4:
                    createNewCamp();
                    printMenu();
                    break;
                case 5:
                    editCreatedCamps();
                    printMenu();
                    break;
                case 6:
                    deleteCreatedCamps();
                    printMenu();
                    break;
                case 7:
                    break;
                default:
                    break;
            }
        } while (choice != 7);

        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of all the Staff's Created Camps
     */
    public void viewAllCreatedCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Created Camps");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        if (camps.size() == 0)
            System.out.println(" No camps found.\n");
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Prints a list of all participants of the Staff's Created Camps
     */
    public void viewCreatedCampParticipants() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Created Camp Participants/Committee");

        ArrayList<Camp> campList = CampController.getStaffCamps(userId);
        for (int i = 0; i < campList.size(); i++)
            common.printCampParticipants(campList.get(i), i + 1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for filtering created Camps
     */
    public void viewFilteredCreatedCamps() {
        filterCampsView.filterCamps("Filter Created Camps", common, CampController.getStaffCamps(userId));
        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for creating a new Camp
     */
    public void createNewCamp() {
        HelperUtil.clearScreen();
        printMenuTitle("Create New Camp");

        String name = "", location = "", description = "";
        Faculty userGroup = StaffController.getStaffByUserId(userId).getFaculty();
        Date startDate = null, endDate = null, registrationCloseDate = null;
        int totalSlots = -1, commSlots = -1;

        do {
            System.out.print("Enter Camp Name: ");
            name = HelperUtil.nextString();

            if (name.equals(""))
                System.out.println("Invalid name, please try again.\n");
            else if (!CampController.checkCampNameUnique("", name)) {
                System.out.println("Duplicate camp name, please try again.");
                name = "";
            }
        } while (name.equals(""));

        do {
            System.out.print("Enter Camp Description: ");
            description = HelperUtil.nextString();

            if (description.equals(""))
                System.out.println("Invalid description, please try again.\n");
        } while (description.equals(""));

        do {
            System.out.print("Enter Camp Location: ");
            location = HelperUtil.nextString();

            if (location.equals(""))
                System.out.println("Invalid location, please try again.\n");
        } while (location.equals(""));

        do {
            System.out.print("Enter Camp Start Date (dd/MM/yyy): ");
            startDate = ForDate.getDates(HelperUtil.nextString());

            if (startDate == null)
                System.out.println("Invalid date, please try again.");
            else if (startDate.before(new Date())) {
                System.out.println("Start Date cannot be before today, please try again.");
                startDate = null;
            }
        } while (startDate == null);

        do {
            System.out.print("Enter Camp End Date (dd/MM/yyy): ");
            endDate = ForDate.getDates(HelperUtil.nextString());

            if (endDate == null)
                System.out.println("Invalid date, please try again.");
            else if (endDate.before(startDate)) {
                System.out.println("End Date cannot be before Start Date, please try again.");
                endDate = null;
            }
        } while (endDate == null);

        do {
            System.out.print("Enter Camp Registration Close Date (dd/MM/yyy): ");
            registrationCloseDate = ForDate.getDates(HelperUtil.nextString());

            if (registrationCloseDate == null)
                System.out.println("Invalid date, please try again.");
            else if (registrationCloseDate.after(startDate)) {
                System.out.println("Registration Close Date cannot be after Start Date, please try again.");
                registrationCloseDate = null;
            } else if (registrationCloseDate.before(new Date())) {
                System.out.println("Registration Close Date cannot be before today, please try again.");
                registrationCloseDate = null;
            }
        } while (registrationCloseDate == null);

        System.out.println("Select Camp User Group:\n  1)  Open to All\n  2)  Faculty Only");
        int choice = -1;
        do {
            System.out.print("Enter your choice: ");
            choice = HelperUtil.nextInt(1, 2);
        } while (choice == -1);
        if (choice == 1)
            userGroup = Faculty.NTU;

        do {
            System.out.print("Enter Camp Total Slots: ");
            totalSlots = HelperUtil.nextInt(1);
        } while (totalSlots == -1);

        do {
            System.out.print("Enter Camp Committee Slots: ");
            commSlots = HelperUtil.nextInt(1);

            if (commSlots > totalSlots) {
                System.out.println("Committee Slots cannot be more than Total Slots, please try again.");
                commSlots = -1;
            } else if (commSlots > 10) {
                System.out.println("Committee Slots cannot be more than 10, please try again.");
                commSlots = -1;
            }
        } while (commSlots == -1);

        String campId = CampController.createCamp(name, startDate, endDate, registrationCloseDate, userGroup, location,
                totalSlots,
                commSlots, description, userId);
        StaffController.addCamp(userId, campId);
        System.out.println("Camp successfully created");

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for selecting a Camp to edit
     */
    public void editCreatedCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Edit Created Camps");

        ArrayList<Camp> camps = CampController.getStaffFutureCamps(userId);
        if (camps.size() == 0) {
            System.out.println(" No camps found.\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            return;
        }

        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        do {
            System.out.print("\nSelect a Camp to edit: ");
            index = HelperUtil.nextInt(1, camps.size());
        } while (index == -1);

        editCreatedCamp(camps.get(index - 1), index);
    }

    /**
     * Menu for editing a Camp
     * 
     * @param camp  The Camp that the Staff selected for editing
     * @param index The index of the Camp in the camps list
     */
    public void editCreatedCamp(Camp camp, int index) {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Editing Camp " + index);

        common.printCampDetails(camp, index);
        System.out.println(
                "\nSelect a field to edit\n  1)  Name\n  2)  Description\n  3)  Location\n  4)  Start Date\n  5)  End Date\n  6)  Registration Close Date\n  7)  User Group\n  8)  Total Slots\n  9)  Committee Slots\n  10) Toggle Visibility");

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 10);

            switch (choice) {
                case 1:
                    String name = "";
                    do {
                        System.out.print("Enter New Camp Name: ");
                        name = HelperUtil.nextString();

                        if (name.equals(""))
                            System.out.println("Invalid name, please try again.\n");
                        else if (!CampController.checkCampNameUnique(camp.getCampId(), name)) {
                            System.out.println("Duplicate camp name, please try again.");
                            name = "";
                        }
                    } while (name.equals(""));

                    CampController.updateCampName(camp.getCampId(), name);
                    System.out.println("\nCamp Name successfully updated.");
                    break;

                case 2:
                    String description = "";
                    do {
                        System.out.print("Enter New Camp Description: ");
                        description = HelperUtil.nextString();

                        if (description.equals(""))
                            System.out.println("Invalid description, please try again.\n");
                    } while (description.equals(""));

                    CampController.updateCampDescription(camp.getCampId(), description);
                    System.out.println("\nCamp Description successfully updated.");
                    break;

                case 3:
                    String location = "";
                    do {
                        System.out.print("Enter New Camp Location: ");
                        location = HelperUtil.nextString();

                        if (location.equals(""))
                            System.out.println("Invalid location, please try again.\n");
                    } while (location.equals(""));

                    CampController.updateCampLocation(camp.getCampId(), location);
                    System.out.println("\nCamp Location successfully updated.");
                    break;

                case 4:
                    Date startDate = null;
                    do {
                        System.out.print("Enter Camp Start Date (dd/MM/yyy): ");
                        startDate = ForDate.getDates(HelperUtil.nextString());

                        if (startDate == null)
                            System.out.println("Invalid date, please try again.");
                        else if (startDate.before(new Date())) {
                            System.out.println("Start Date cannot be before today, please try again.");
                            startDate = null;
                        }
                    } while (startDate == null);

                    CampController.updateCampStartDate(camp.getCampId(), startDate);
                    System.out.println("\nCamp Start Date successfully updated.");
                    break;

                case 5:
                    Date endDate = null;
                    do {
                        System.out.print("Enter New Camp End Date (dd/MM/yyy): ");
                        endDate = ForDate.getDates(HelperUtil.nextString());

                        if (endDate == null)
                            System.out.println("Invalid date, please try again.");
                        else if (endDate.before(camp.getStartDate())) {
                            System.out.println("End Date cannot be before Start Date, please try again.");
                            endDate = null;
                        }
                    } while (endDate == null);

                    CampController.updateCampEndDate(camp.getCampId(), endDate);
                    System.out.println("\nCamp End Date successfully updated.");
                    break;

                case 6:
                    Date registrationCloseDate = null;
                    do {
                        System.out.print("Enter New Camp Registration Close Date (dd/MM/yyy): ");
                        registrationCloseDate = ForDate.getDates(HelperUtil.nextString());

                        if (registrationCloseDate == null)
                            System.out.println("Invalid date, please try again.");
                        else if (registrationCloseDate.after(camp.getStartDate())) {
                            System.out.println("Registration Close Date cannot be after Start Date, please try again.");
                            registrationCloseDate = null;
                        } else if (registrationCloseDate.before(new Date())) {
                            System.out.println("Registration Close Date cannot be before today, please try again.");
                            registrationCloseDate = null;
                        }
                    } while (registrationCloseDate == null);

                    CampController.updateCampRegistrationCloseDate(camp.getCampId(), registrationCloseDate);
                    System.out.println("\nCamp Registration Close Date successfully updated.");
                    break;

                case 7:
                    Faculty userGroup = StaffController.getStaffByUserId(userId).getFaculty();
                    System.out.println("Select New Camp User Group:\n  1)  Open to All\n  2)  Faculty Only");
                    int userGroupChoice = -1;
                    do {
                        System.out.print("Enter your choice: ");
                        userGroupChoice = HelperUtil.nextInt(1, 2);
                    } while (userGroupChoice == -1);
                    if (userGroupChoice == 1)
                        userGroup = Faculty.NTU;

                    CampController.updateCampUserGroup(camp.getCampId(), userGroup);
                    System.out.println("\nCamp User Group successfully updated.");
                    break;

                case 8:
                    int totalSlots = -1;
                    do {
                        System.out.print("Enter New Camp Total Slots: ");
                        totalSlots = HelperUtil.nextInt(1);

                        if (totalSlots < camp.getCommSlots())
                            System.out.println("Total Slots cannot be lesser than Committee Slots, please try again.");
                    } while (totalSlots == -1);

                    CampController.updateCampTotalSlots(camp.getCampId(), totalSlots);
                    System.out.println("\nCamp Total Slots successfully updated.");
                    break;

                case 9:
                    int commSlots = -1;
                    do {
                        System.out.print("Enter New Camp Committee Slots: ");
                        commSlots = HelperUtil.nextInt(1);

                        if (commSlots > camp.getTotalSlots()) {
                            System.out.println("Committee Slots cannot be more than Total Slots, please try again.");
                            commSlots = -1;
                        } else if (commSlots > 10) {
                            System.out.println("Committee Slots cannot be more than 10, please try again.");
                            commSlots = -1;
                        }
                    } while (commSlots == -1);

                    CampController.updateCampCommSlots(camp.getCampId(), commSlots);
                    System.out.println("\nCamp Committee Slots successfully updated.");
                    break;

                case 10:
                    if (camp.getRemainingParticipantSlots() < camp.getParticipantSlots()
                            || camp.getRemainingCommitteeSlots() < camp.getCommSlots()) {
                        System.out.println("Unable to toggle camp visibility, camp has registered students.");
                        break;
                    }

                    System.out.println("Toggle Camp Visibility:\n  1)  Visible\n  2)  Not Visible");
                    int visibility = -1;
                    do {
                        System.out.print("Enter your choice: ");
                        visibility = HelperUtil.nextInt(1, 2);
                    } while (visibility == -1);

                    if (visibility == 1) {
                        CampController.updateCampVisiblity(camp.getCampId(), true);
                        System.out.println("Camp set to Visible.");
                    } else if (visibility == 2) {
                        CampController.updateCampVisiblity(camp.getCampId(), false);
                        System.out.println("Camp set to Not Visible.");
                    }

                    System.out.println("\nCamp Visibility successfully updated.");
                    break;

                default:
                    break;
            }
        } while (choice == -1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for selecting a Camp to delete
     */
    public void deleteCreatedCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Delete Created Camps");

        ArrayList<Camp> camps = CampController.getStaffCamps(userId);
        if (camps.size() == 0) {
            System.out.println(" No camps found.\n");
            HelperUtil.pressAnyKeyToContinue();
            HelperUtil.clearScreen();
            return;
        }

        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        do {
            System.out.print("\nSelect an Camp to delete: ");
            index = HelperUtil.nextInt(1, camps.size());
        } while (index == -1);

        deleteCreatedCamp(camps.get(index - 1), index);
    }

    /**
     * Menu for deleting a Camp
     * 
     * @param camp  The Camp that the Staff selected for deletion
     * @param index The index of the Camp in the camps list
     */
    public void deleteCreatedCamp(Camp camp, int index) {
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Delete Camp " + index);

        common.printCampDetails(camp, index);

        do {
            System.out.print("\nAre you sure you want to delete this camp? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                if (camp.getRemainingParticipantSlots() < camp.getParticipantSlots()
                        || camp.getRemainingCommitteeSlots() < camp.getCommSlots()) {
                    System.out.println("Unable to delete camp, camp has registered students.");
                    break;
                }

                CampController.deleteCamp(camp.getCampId());
                System.out.println("Camp successfully deleted.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }
}
