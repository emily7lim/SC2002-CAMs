import database.Database;

public class App {
    public static void main(String[] args) {
        new Database();
        Database.USERS.forEach((userId, user) -> {
            System.out.println("UserID: " + userId + ", Name: " + user.getName());
        });
    }
}
