package controller;

import database.UserDAO;
import model.Staff;
import model.Student;
import model.User;
import model.enums.Faculty;
import model.enums.Role;

public class UserController {
    /**
     * Creates a new User and adds to the Database
     * @param userId The User ID of the new User
     * @param name The name of the new User
     * @param password The password of the new User
     * @param faculty The faculty of the new User
     * @param role The Role of the new User
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
     * @param userId The User ID of the User
     * @param password The password of the User
     * @return boolean Whether the User credentials matches any User in the database 
     */
    public static boolean validateUserCredentials(String userId, String password) {
        if (!checkUserExists(userId)) return false;

        return UserDAO.getUserbyId(userId).getPassword() == password;
    }

    /**
     * Check if the User with User ID exists in the database
     * @param userId
     * @return
     */
    public static boolean checkUserExists(String userId) {
        return UserDAO.checkUser(userId);
    }
}
