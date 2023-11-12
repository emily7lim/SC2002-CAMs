package database;

import model.User;
import model.enums.Role;

public class UserDAO {
    /** 
     * Adds a user to the database
     * @param user The user Object to save
     */
    public static void createUser(User user) {
        Database.USERS.put(user.getUserId(), user);
    }

    /** 
     * Finds a user from the database using the ID
     * @param userId The User ID of the user  
     * @return User The corresponding user object, NULL if not found
     */
    public static User getUserbyId(String userId) {
        return Database.USERS.get(userId);
    }

    /** 
     * Check if a User exists in the database using the ID
     * @param userId The User ID of the User
     * @return boolean Whether the User exists
     */
    public static boolean checkUser(String userId) {
        return Database.USERS.containsKey(userId);
    }

    /**
     * Check if a User has a specified Role in the database using the ID
     * @param userId The User ID of the User
     * @param role The Role to check
     * @return boolean Whether the User has the Role
     */
    public static boolean checkUserRole(String userId, Role role) {
        User user = getUserbyId(userId);

        if (user != null && user.getRole() == role) return true;
        else return false;
    }

    public static void updateUserPassword(String userId, String password) {
        Database.USERS.get(userId).setPassword(password);
    }

    /** 
     * Removes a user from the database using the ID
     * @param userId The user ID of the user
     */
    public static void deleteUser(String userId) {
        Database.USERS.remove(userId);
    }
}
