import controller.EnquiryController;
import controller.SuggestionController;
import database.Database;
import utils.HelperUtil;
import view.AppView;

public class App {
    public static void main(String[] args) {
        AppView appView = new AppView();
        Database database = new Database();

        SuggestionController.createSuggestion("1b1e6872-5e51-4259-bd07-8e10d2925632", "YCHERN", "Test Suggestion");
        EnquiryController.createEnquiry("1b1e6872-5e51-4259-bd07-8e10d2925632", "YCHERN", "Test Enquiry");

        HelperUtil.clearScreen();
        printApplicationTitle();
        appView.viewMenu();

        // database.saveAllToDatabase();
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
