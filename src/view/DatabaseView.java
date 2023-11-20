package view;

import database.Database;
import database.FileName;
import utils.HelperUtil;

public class DatabaseView extends MainView {
    private final String MENU_TITLE = "Manage Database";
    private Database database;

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  Reset Database");
        System.out.println("  2)  Initialize Users");
        System.out.println("  3)  Initialize Camps");
        System.out.println("  4)  Back");
    }

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
