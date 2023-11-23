package view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

import model.Camp;
import utils.ForDate;
import utils.HelperUtil;

/**
 * User Interface for filtering Camps
 * 
 * @author Emily, Chloie
 * @version 2.1.1
 * @since 2023-11-22
 */
public class FilterCampsView extends MainView {
    private String title;
    private CommonUse common;
    private ArrayList<Camp> camps;

    /**
     * Get dependencies and invoke filter Camps application
     * 
     * @param title  The title of the current filter Camps menu
     * @param common An instance of the CommonUse object
     * @param camps  The array of Camps to be filtered
     */
    public void filterCamps(String title, CommonUse common, ArrayList<Camp> camps) {
        this.title = title;
        this.common = common;
        this.camps = camps;
        viewMenu();
    }

    /**
     * Menu for filtering Camps
     */
    public void printMenu() {
        printMenuTitle(title);
        System.out.println("Select a field to filter:");
        System.out.println("  1)  Name");
        System.out.println("  2)  Location");
        System.out.println("  3)  Start Date");
        System.out.println("  4)  End Date");
        System.out.println("  5)  Registration Close Date");
        System.out.println("  6)  User Group");
        System.out.println("  7)  Total Slots");
        System.out.println("  8)  Committee Slots");
    }

    /**
     * Application for filtering Camps
     */
    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();

        if (camps.size() == 0) {
            printMenuTitle(title);
            System.out.println(" No camps found.\n");
            return;
        }

        printMenu();
        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 8);

            switch (choice) {
                case 1:
                    String name = "";
                    do {
                        System.out.print("Enter Camp Name: ");
                        name = HelperUtil.nextString();

                        if (name.equals(""))
                            System.out.println("Invalid name, please try again.\n");
                    } while (name.equals(""));

                    printNameFilteredCamps(name);
                    break;

                case 2:
                    String location = "";
                    do {
                        System.out.print("Enter Camp Location: ");
                        location = HelperUtil.nextString();

                        if (location.equals(""))
                            System.out.println("Invalid location, please try again.\n");
                    } while (location.equals(""));

                    printLocationFilteredCamps(location);
                    break;

                case 3:
                    Date startDate = null;
                    do {
                        System.out.print("Enter Camp Start Date (dd/MM/yyy): ");
                        startDate = ForDate.getDates(HelperUtil.nextString());
                        if (startDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (startDate == null);

                    printStartDateFilteredCamps(startDate);
                    break;

                case 4:
                    Date endDate = null;
                    do {
                        System.out.print("Enter Camp End Date (dd/MM/yyy): ");
                        endDate = ForDate.getDates(HelperUtil.nextString());
                        if (endDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (endDate == null);

                    printEndDateFilteredCamps(endDate);
                    break;

                case 5:
                    Date registrationCloseDate = null;
                    do {
                        System.out.print("Enter Camp Registration Close Date (dd/MM/yyy): ");
                        registrationCloseDate = ForDate.getDates(HelperUtil.nextString());
                        if (registrationCloseDate == null)
                            System.out.println("Invalid date, please try again.");
                    } while (registrationCloseDate == null);

                    printRegistrationCloseDateFilteredCamps(registrationCloseDate);
                    break;

                case 6:
                    String faculty = "";
                    do {
                        System.out.print("Enter Faculty: ");
                        faculty = HelperUtil.nextString();
                        if (faculty.equals(""))
                            System.out.println("Invalid faculty, please try again.");
                    } while (faculty.equals(""));

                    printFacultyFilteredCamps(faculty);
                    break;

                case 7:
                    int totalSlots = -1;
                    do {
                        System.out.print("Enter Camp Total Slots: ");
                        totalSlots = HelperUtil.nextInt(1);
                    } while (totalSlots == -1);

                    printTotalSlotsFilteredCamps(totalSlots);
                    break;

                case 8:
                    int commSlots = -1;
                    do {
                        System.out.print("Enter Committee Slots: ");
                        totalSlots = HelperUtil.nextInt(1);
                    } while (totalSlots == -1);

                    printCommSlotsFilteredCamps(commSlots);
                    break;
                default:
                    break;
            }
        } while (choice == -1);
    }

    /**
     * Prints a list of the Camps filtered by Name
     * 
     * @param name The name that the Camps should be filtered by
     */
    public void printNameFilteredCamps(String name) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Name");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by Location
     * 
     * @param location The location that the Camps should be filtered by
     */
    public void printLocationFilteredCamps(String location) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Location");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getLocation().toLowerCase().contains(location.toLowerCase()))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by Start Date
     * 
     * @param startDate The Start Date that the Camps should be filtered by
     */
    public void printStartDateFilteredCamps(Date startDate) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Start Date");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getStartDate().equals(startDate))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by End Date
     * 
     * @param endDate The End Date that the Camps should be filtered by
     */
    public void printEndDateFilteredCamps(Date endDate) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by End Date");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getEndDate().equals(endDate))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by Registration Close Date
     * 
     * @param registrationCloseDate The Registration Close Date that the Camps
     *                              should be filtered by
     */
    public void printRegistrationCloseDateFilteredCamps(Date registrationCloseDate) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Registration Close Date");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getRegistrationCloseDate().equals(registrationCloseDate))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by Faculty
     * 
     * @param faculty The Faculty that the Camps should be filtered by
     */
    public void printFacultyFilteredCamps(String faculty) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Faculty");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getUserGroup().getFaculty().toLowerCase().contains(faculty.toLowerCase())
                        || camp.getUserGroup().toString().toLowerCase().equals(faculty.toLowerCase()))
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by Total number of Slots
     * 
     * @param totalSlots The Total number of Slots that the Camps should be filtered
     *                   by
     */
    public void printTotalSlotsFilteredCamps(int totalSlots) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Total Slots");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getTotalSlots() == totalSlots)
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of the Camps filtered by number of Committee Slots
     * 
     * @param commSlots The number of Committee Slots that the Camps should be
     *                  filtered by
     */
    public void printCommSlotsFilteredCamps(int commSlots) {
        HelperUtil.clearScreen();
        printMenuTitle("List of Filtered Camps by Committee Slots");

        ArrayList<Camp> filteredCamps = new ArrayList<>(camps.stream()
                .filter(camp -> camp.getCommSlots() == commSlots)
                .sorted(Comparator.comparing(Camp::getName))
                .collect(Collectors.toList()));

        printFilteredCamps(filteredCamps);
    }

    /**
     * Prints a list of filtered Camps
     * 
     * @param filteredCamps The list of filtered Camps to be printed
     */
    public void printFilteredCamps(ArrayList<Camp> filteredCamps) {
        if (filteredCamps.size() == 0)
            System.out.println(" No camps found.\n");
        else {
            for (int i = 0; i < filteredCamps.size(); i++)
                common.printCampDetails(filteredCamps.get(i), i + 1);
        }
    }
}
