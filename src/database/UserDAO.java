package database;

import model.User;

public class UserDAO {
    public static User getUserbyId(String userId) {
        return Database.USERS.get(userId);
    }
    
    public static void createUser(User user) {
        Database.USERS.put(user.getUserId(), user);
    }
}
