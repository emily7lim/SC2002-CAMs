package view;

import database.Database;
import utils.HelperUtil;

/**
 * User Interface for Main Menu of the application
 * 
 * @author Chloie
 * @version 1.1.2
 * @since 2023-11-19
 */
public class AppView extends MainView {
    /**
     * Main Menu Title
     */
    private final String MENU_TITLE = "Main Menu";

    /**
     * LoginView object for Login User Interface
     */
    private LoginView loginView;
    /**
     * DatabaseView object for Database Menu
     */
    private DatabaseView databaseView;
    /**
     * Database object to intialize and save data
     */
    private Database database;

    /**
     * Default constructor
     */
    public AppView() {
        loginView = new LoginView();
        databaseView = new DatabaseView();
    }
    
    /**
     * Sets the Database object of the application instance 
     * @param database The Database object of the application instance
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * Menu for the Main Menu interface
     */
    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  Login");
        System.out.println("  2)  Manage Database");
        System.out.println("  3)  Exit");
    }

    /**
     * Application for the Main Menu
     */
    public void viewMenu() {
        int choice = -1;
        printMenu();

        do {
            System.out.print("\nEnter your choice: ");
            choice = HelperUtil.nextInt(1, 3);

            switch (choice) {
                case 1:
                    loginView.viewMenu();
                    printMenu();
                    break;
                case 2:
                    databaseView.setDatabase(database);
                    databaseView.viewMenu();
                    printMenu();
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        } while (choice != 3);
    }
}