package view;

import database.Database;
import utils.HelperUtil;

public class AppView extends MainView {
    private final String MENU_TITLE = "Main Menu";

    private LoginView loginView;
    private DatabaseView databaseView;
    private Database database;

    public AppView() {
        loginView = new LoginView();
        databaseView = new DatabaseView();
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
        System.out.println("  1)  Login");
        System.out.println("  2)  Manage Database");
        System.out.println("  3)  Exit");
    }

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