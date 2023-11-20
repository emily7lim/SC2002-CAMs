import database.Database;
import utils.HelperUtil;
import view.AppView;

public class App {
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
