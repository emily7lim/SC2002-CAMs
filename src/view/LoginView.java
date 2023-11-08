package view;

public class LoginView {
    public void login() {
        boolean loggedIn = false;

        do {
            System.out.print("User ID: ");
            System.out.print("Password: ");

        } while (!loggedIn);

        //on login pg, faculty info can take from database
        // if (user == "student") {
        //     if (user == "committee") CommitteeView.main(null);
        //     else StudentView.main(null);
        // }
        // else if (user == "staff") StaffView.main(null);
        // else relogin?
        
    }
}