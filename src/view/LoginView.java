package view;

import controller.CampController;
import controller.StudentController;
import controller.UserController;
import utils.HelperUtil;

/**
 * User Interface for User Login
 * 
 * @author Emily, Chloie
 * @version 1.2.1
 * @since 2023-10-30
 */
public class LoginView extends MainView {
    private final String MENU_TITLE = "Login";

    private StaffView staffView;
    private StudentView studentView;
    private CommitteeView committeeView;

    /**
     * Default constructor
     */
    public LoginView() {
        staffView = new StaffView();
        studentView = new StudentView();
        committeeView = new CommitteeView();
    }

    /**
     * Menu for the User Login interface
     */
    public void printMenu() {
        printMenuTitle(MENU_TITLE);
    }

    /**
     * Application for the User Login Menu
     */
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
                        studentView.setUserId(userId);
                        studentView.viewMenu();
                        break;
                    case COMMITTEE:
                        loggedIn = true;
                        if (checkCommittee(userId)) {
                            committeeView.setUserId(userId);
                            committeeView.viewMenu();
                        } else {
                            studentView.setUserId(userId);
                            studentView.viewMenu();
                        }
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

    /**
     * Check if the logged in User is currently a Camp Committee
     * 
     * @param userId The User ID of the logged in User
     * @return boolean Whether the logged in User is currently a Camp Committee
     */
    public boolean checkCommittee(String userId) {
        if (CampController.checkCurrentCampCommittee(userId))
            return true;
        else {
            StudentController.removeCampCommittee(userId);
            return false;
        }
    }
}