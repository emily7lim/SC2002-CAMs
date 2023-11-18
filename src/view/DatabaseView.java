package view;

import utils.HelperUtil;

public class DatabaseView extends MainView {
    private final String MENU_TITLE = "Manage Database";

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
    }

    public void viewMenu() {
        HelperUtil.clearScreen();
        printMenu();
    }
    
}
