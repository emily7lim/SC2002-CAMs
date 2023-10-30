package controller;

import database.UserDAO;
import model.Student;
import model.User;
import model.enums.Faculty;
import model.enums.Role;

public class UserManager {
    public static User createUser(String userId, String name, String password, Faculty faculty, Role role) {
        User user = null;

        switch (role) {
            case STUDENT:
                user = new Student(userId, name, password, faculty);
                UserDAO.createUser(user);
                break;
            default:
                break;
        }

        return user;
    }

    public static void updatePassword(String userId) {
        // Code here
    }

    public static boolean validateUserCredentials(String userId, String password) {
        User user = UserDAO.getUserbyId(userId);

        return true;
    }
}
