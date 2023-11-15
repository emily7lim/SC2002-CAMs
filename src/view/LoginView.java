package view;

import java.util.Scanner;

import controller.*;
import model.enums.Role;

public class LoginView {
    public void login() {
        boolean loggedIn = false;

        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("1) Login\n2) Terminate");
            Integer input = CommonUse.dataValidation();
            if (input == 1){
                System.out.println("User ID: ");
            String id = sc.nextLine().toUpperCase();
            System.out.println("Password: ");
            String pw = sc.nextLine();
            if (UserController.validateUserCredentials(id, pw)) {
                try {
                    if (StaffController.getStaffByUserId(id).getRole() == Role.STAFF) {
                        System.out.println("Logged in as staff!");
                        loggedIn = true;
                        Integer choice = 0;
                        do {
                            System.out.println("\n------------------Staff Menu------------------\n1) View all camps and students registered\n2) See list of camps I created\n3) Profile\n4) Enquiries from student\n5) Generate report of students\n6) Generate performance report of committee members\n7) Suggestions from camp committee\n8) Results of enquiries/suggestions\n9) Change password\n10) Quit");
                            System.out.println("----------------------------------------------\nSelect your choice:");
                            
                            choice = CommonUse.dataValidation();
                            StaffView.Staff(choice, id);

                        } while (choice != 10);
                        System.out.println("Logged out\n");
                        loggedIn = false;

                    }

                } catch (Exception e) {
                    try {
                        if (StudentController.getStudentByUserId(id).getRole() == Role.STUDENT) {
                            System.out.println("Logged in as student!");

                            loggedIn = true;
                            Integer choice = 0;
                            do {
                                System.out.println(
                                        "\n------------------Student Menu------------------\n1) View list of camp\n2) Register for camps\n3) Submit enquiries\n4) View/edit/del enquiries\n5) View registered camps\n6) Withdraw from camp \n7) View Replies\n8) Change password\n9) Profile\n10) Quit");
                                System.out.println(
                                        "------------------------------------------------\nSelect your choice:");
                                choice = CommonUse.dataValidation();
                                StudentView.Students(choice, id);

                            } while (choice != 10);
                            System.out.println("Logged out\n");
                            loggedIn = false;

                        } else if (StudentController.getStudentByUserId(id).getRole() == Role.COMMITTEE) {
                            System.out.println("Logged in as committee!");
                            loggedIn = true;
                            Integer choice = 0;
                            do {
                                System.out.println("------------------Committee Menu------------------\n1) View list of camp\n2) Register for camps\n3) Submit enquiries\n4) View/edit/del enquiries\n5) View registered camps\n6) Withdraw from camp \n7) View Replies\n8) Change password\n9) Profile\n10) Submit suggestions\n11) Enquiries from student \n12) View,edit,delete suggestions\n13) Generate student list report\n14) Quit");
                                System.out.println("--------------------------------------------------\nSelect your choice:");
            
                                choice = CommonUse.dataValidation();
                                CommitteeView.Committee(choice, id);
                            } while (choice != 14);
                            System.out.println("Logged out\n");
                            loggedIn = false;
                        }

                    } catch (Exception er) {
                    }
                }

            } else
                System.out.println("Invalid credentials");
            }
            else if (input == 2) loggedIn = true;
            
        } while (!loggedIn);
    }
}