package view;

public class LoginView {
    public void login() {
        boolean loggedIn = false;

        do {
            System.out.print("User ID: ");
            System.out.print("Password: ");

        } while (!loggedIn);

        //on login pg, faculty info can take from database
    }
}