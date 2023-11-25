import database.Database;
import utils.HelperUtil;
import view.AppView;

/**
 * The starting point of the application
 * 
 * @author Emily, Owen, Chloie
 * @version 2.2.2
 * @since 2023-10-30
 */
public class App {
    /**
     * Main function of the application
     * 
     * @param args Arguments passed to the application
     */
    public static void main(String[] args) {
        AppView appView = new AppView();
        Database database = new Database();

        HelperUtil.clearScreen();
        printApplicationTitle();
        appView.setDatabase(database);
        appView.viewMenu();

        database.saveAllToDatabase();
        System.out.println("\nClosing CAMs...");
    }

    /**
     * Print the application title
     */
    public static void printApplicationTitle() {
        System.out.println("======================================================================\n\n" +
                "              ______     ___      .___  ___.      _______.\n" + //
                "             /      |   /   \\     |   \\/   |     /       |\n" + //
                "            |  ,----'  /  ^  \\    |  \\  /  |    |   (----`\n" + //
                "            |  |      /  /_\\  \\   |  |\\/|  |     \\   \\    \n" + //
                "            |  `----./  _____  \\  |  |  |  | .----)   |   \n" + //
                "             \\______/__/     \\__\\ |__|  |__| |_______/    \n\n" + //
                "               Camp Application and Management System\n\n" + //
                "======================================================================\n");
    }
}
