package view;

import database.Database;
import database.FileName;
import utils.HelperUtil;

/**
 * User Interface for Managing Database
 * 
 * @author Chloie
 * @version 1.1.2
 * @since 2023-11-19
 */
public class DatabaseView extends MainView {
    private final String MENU_TITLE = "Manage Database";
    private Database database;

    /**
     * Sets the Database object of the application
     * 
     * @param database The Database object of the application
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Menu for Managing Database interface
     */
    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  Reset Database");
        System.out.println("  2)  Initialize Users");
        System.out.println("  3)  Initialize Camps");
        System.out.println("  4)  Back");
    }

    /**
     * Application for Managing Database
     */
    public void viewMenu() {
        int choice = -1;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.printf("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 4);

            switch (choice) {
                case 1:
                    resetDatabase();
                    printMenu();
                    break;

                case 2:
                    initializeUserData();
                    printMenu();
                    break;

                case 3:
                    initializeCampData();
                    printMenu();
                    break;

                case 4:
                    break;
                default:
            }
        } while (choice != 4);

        HelperUtil.clearScreen();
    }

    /**
     * Menu for resetting application's Database
     */
    public void resetDatabase() {
        String confirm = "";
        HelperUtil.clearScreen();
        printMenuTitle("Reset Database");

        do {
            System.out.print("\nAre you sure you want to reset the database? (y/n) ");
            confirm = HelperUtil.nextString().toLowerCase();

            if (confirm.equals("y")) {
                database.clearDatabase();
                System.out.println("Database successfully resetted.\n");
                break;
            } else if (!confirm.equals("n"))
                System.out.println("Invalid input, please try again.");
            else
                break;
        } while (true);

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for initializing dummy User data
     */
    public void initializeUserData() {
        String confirm = "";
        HelperUtil.clearScreen();
        printMenuTitle("Initialize Users");

        if (!database.checkDataEmpty(FileName.USERS))
            System.out.println("Please reset the database before initializing data.");
        else {
            do {
                System.out.print("\nAre you sure you want to initialize users? (y/n) ");
                confirm = HelperUtil.nextString().toLowerCase();

                if (confirm.equals("y")) {
                    database.initializeUserData();
                    System.out.println("User data initialized successfully.\n");
                    break;
                } else if (!confirm.equals("n"))
                    System.out.println("Invalid input, please try again.");
                else
                    break;
            } while (true);
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }

    /**
     * Menu for initializing dummy Camp data
     */
    public void initializeCampData() {
        String confirm = "";
        HelperUtil.clearScreen();
        printMenuTitle("Initialize Camps");

        if (!database.checkDataEmpty(FileName.CAMPS))
            System.out.println("Please reset the database before initializing data.");
        else {
            do {
                System.out.print("\nAre you sure you want to initialize camps? (y/n) ");
                confirm = HelperUtil.nextString().toLowerCase();

                if (confirm.equals("y")) {
                    database.initializeCampData();
                    System.out.println("Camp data initialized successfully.\n");
                    break;
                } else if (!confirm.equals("n"))
                    System.out.println("Invalid input, please try again.");
                else
                    break;
            } while (true);
        }

        HelperUtil.pressAnyKeyToContinue();
        HelperUtil.clearScreen();
    }
}
