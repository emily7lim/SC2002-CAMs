package view;

import java.util.*;

import controller.*;
import model.Camp;
import model.enums.Faculty;
import utils.HelperUtil;

public class CreatedCampsView extends MainView {
    private final String MENU_TITLE = "Manage Created Camps";

    private String userId;
    private CommonUse common;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCommon(CommonUse common) {
        this.common = common;
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  View All Created Camps");
        System.out.println("  2)  Create New Camp");
        System.out.println("  3)  Edit Created Camp");
        System.out.println("  4)  Delete Created Camp");
        System.out.println("  5)  Back");
    }

    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 5);

            switch (choice) {
                case 1:
                    viewAllCreatedCamps();
                    printMenu();
                    break;
                case 2:
                    createNewCamp();
                    printMenu();
                    break;
                case 3:
                    editCreatedCamps();
                    printMenu();
                    break;
                case 4:
                    deleteCreatedCamps();
                    printMenu();
                    break;
                case 5:
                    break;
                default:
                    break;
            }
        } while (choice != 5);

        HelperUtil.clearScreen();
    }

    public void viewAllCreatedCamps() {
        HelperUtil.clearScreen();
        printMenuTitle("List of Created Camps");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

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

            if (commSlots > totalSlots)
                System.out.println("Committee Slots cannot be more than Total Slots, please try again.");
        } while (commSlots == -1);

        CampController.createCamp(name, startDate, endDate, registrationCloseDate, userGroup, location, totalSlots,
                commSlots, description, userId);
        System.out.println("Camp successfully created");

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    public void editCreatedCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Edit Created Camps");

        ArrayList<Camp> camps = CampController.getFutureCampsByStaffInCharge(userId);
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        System.out.print("Select a Camp to edit: ");
        do {
            System.out.print("Select an Camp to edit: ");
            index = HelperUtil.nextInt(1, camps.size());
        } while (index == -1);

        editCreatedCamp(camps.get(index), index);
    }

    public void editCreatedCamp(Camp camp, int index) {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Editing Camp " + index);

        common.printCampDetails(camp, index);
        System.out.println(
                "\nSelect a field to edit  1)  Name\n  2)  Description\n  3)  Location\n  4)  Start Date\n  5)  End Date\n  6)  Registration Close Date\n  7)  User Group\n  8)  Total Slots\n  9)  Committee Slots\n  10) Toggle Visibility");

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

                        if (commSlots > camp.getTotalSlots())
                            System.out.println("Committee Slots cannot be more than Total Slots, please try again.");
                    } while (commSlots == -1);

                    CampController.updateCampCommSlots(camp.getCampId(), commSlots);
                    System.out.println("\nCamp Committee Slots successfully updated.");
                    break;

                case 10:
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

    public void deleteCreatedCamps() {
        int index = -1;
        HelperUtil.clearScreen();
        printMenuTitle("Delete Created Camps");

        ArrayList<Camp> camps = CampController.getCampsByStaffInCharge(userId);
        for (int i = 0; i < camps.size(); i++)
            common.printCampDetails(camps.get(i), i + 1);

        System.out.print("Select a Camp to delete: ");
        do {
            System.out.print("Select an Camp to delete: ");
            index = HelperUtil.nextInt(1, camps.size());
        } while (index == -1);

        deleteCreatedCamp(camps.get(index), index);
    }

    public void deleteCreatedCamp(Camp camp, int index) {
        String confirm;
        HelperUtil.clearScreen();
        printMenuTitle("Delete Camp " + index);

        common.printCampDetails(camp, index);

        do {
            System.out.print("\nAre you sure you want to delete this camp? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
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
