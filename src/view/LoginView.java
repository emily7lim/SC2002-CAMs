package view;

import controller.UserController;
import utils.HelperUtil;

public class LoginView extends MainView {
    private final String MENU_TITLE = "Login";

    private StaffView staffView;
    private StudentView studentView;
    private CommitteeView committeeView;

    public LoginView() {
        staffView = new StaffView();
        studentView = new StudentView();
        committeeView = new CommitteeView();
    }

    public void printMenu() {
        printMenuTitle(MENU_TITLE);
    }

    public void viewMenu() {
        boolean loggedIn = false;
        String userId, password;
        HelperUtil.clearScreen();
        printMenu();

        do {
            System.out.print("Enter your User ID: ");
            userId = HelperUtil.nextString().toUpperCase();

            System.out.print("Enter your Password: ");
            password = HelperUtil.nextString();

            if (UserController.validateUserCredentials(userId, password)) {
                switch (UserController.getUserRoleByUserId(userId)) {
                    case STAFF:
                        loggedIn = true;
                        staffView.setUserId(userId);
                        staffView.viewMenu();
                        break;
                    case STUDENT:
                        loggedIn = true;
                        studentView.Students(0, userId);
                        break;
                    case COMMITTEE:
                        loggedIn = true;
                        committeeView.Committee(0, userId);
                        break;
                    default:
                        System.out.println("Unable to login, try again.");
                        break;
                }
            } else
                System.out.println("Invalid credentials, try again.\n");
        } while (!loggedIn);

        HelperUtil.clearScreen();
    }
}