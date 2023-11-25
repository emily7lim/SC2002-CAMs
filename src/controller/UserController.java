package controller;

import database.Database;
import database.UserDAO;
import model.Staff;
import model.Student;
import model.User;
import model.enums.Faculty;
import model.enums.Role;

/**
 * UserController class to manage User and check for user validation
 * @author Chloie
 * @version 1.1.5
 * @since 2023-11-04
 */
public class UserController {
    /**
     * Creates a new User and adds to the Database
     * 
     * @param userId   The User ID of the new User
     * @param name     The name of the new User
     * @param password The password of the new User
     * @param faculty  The faculty of the new User
     * @param role     The Role of the new User
     */
    public static void createUser(String userId, String name, String password, Faculty faculty, Role role) {
        User user = null;

        switch (role) {
            case STAFF:
                user = new Staff(userId, name, password, faculty);
                UserDAO.createUser(user);
            case STUDENT:
                user = new Student(userId, name, password, faculty);
                UserDAO.createUser(user);
                break;
            case COMMITTEE:
                user = new Student(userId, name, password, faculty, Role.COMMITTEE);
                UserDAO.createUser(user);
                break;
            default:
                break;
        }
    }

    /**
     * Check if the User credentials matches any User in the Database
     * 
     * @param userId   The User ID of the User
     * @param password The password of the User
     * @return boolean Whether the User credentials matches any User in the database
     */
    public static boolean validateUserCredentials(String userId, String password) {
        if (!checkUserExists(userId))
            return false;

        return UserDAO.getUserbyId(userId).validatePassword(password);
    }

    /**
     * Check if the User with User ID exists in the database
     * 
     * @param userId The User ID of the User
     * @return boolean Whether the User exists in the database
     */
    public static boolean checkUserExists(String userId) {
        return UserDAO.checkUser(userId);
    }

    /**
     * Finds a User from the Database by the User ID
     * 
     * @param userId The User ID of the User
     * @return User The corresponding User object, NULL if not found
     */
    public static User getUserByUserId(String userId) {
        return UserDAO.getUserbyId(userId);
    }

    /**
     * Finds the Role of a User from the Database by the User ID
     * 
     * @param userId The User ID of the User
     * @return Role The corresponding Role of the User, NULL if not found
     */
    public static Role getUserRoleByUserId(String userId) {
        User user = UserDAO.getUserbyId(userId);
        return user == null ? null : user.getRole();
    }

    /**
     * Check if it is the first time that a user logs in
     * 
     * @param userId The User ID of the logged in User
     * @return Whether it is the first time that a user logs in
     */
    public static boolean checkUserFirstLogin(String userId) {
        User user = UserDAO.getUserbyId(userId);
        return user.getLoginCount() == 0;
    }

    /**
     * Change the password of the User with User ID in the database
     * 
     * @param userId   The User ID of the User
     * @param password The new password of the User
     * @return boolean Whether the User password was successfully updated
     */
    public static boolean changePassword(String userId, String password) {
        if (!checkUserExists(userId))
            return false;

        UserDAO.updateUserPassword(userId, password);
        return true;
    }

    /**
     * For user login
     * 
     * @param userId   The User ID of the User
     */
    public static void login(String userId) {
        Database.USERS.get(userId).login();
    }
}
