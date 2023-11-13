package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;
import model.enums.EnquiryStatus;

public class StudentView {
    public static void Students(Integer choice, String loggedID) {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;
        CommonUse common = new CommonUse();
        ArrayList<String> getcampcid = new ArrayList<>();
        ArrayList<String> getcampaid = new ArrayList<>();

        // String loggedID = "YCHERN"; // note:take from login detail
        // while (continues) {
        // System.out.println("------------------Student Menu------------------\n1) View
        // list of camp\n2) Register for camps\n3) Submit enquiries\n4) View/edit/del
        // enquiries\n5) View registered camps\n6) Withdraw from camp \n7) View
        // Replies\n8) Change password\n9) Profile\n10) Quit");
        // System.out.println("------------------------------------------------\nSelect
        // your choice:");

        // Integer choice = sc.nextInt();
        switch (choice) {
            case 1: // view list of camps
                // view remaining camp slots
                common.ViewingCamps(loggedID);
                break;

            case 2: // Register camps
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    if (CampController.getAllCamps().get(i).getCommSlots() != 0) {
                        getcampcid.add(CampController.getAllCamps().get(i).getCampId());
                    }
                    if (CampController.getAllCamps().get(i).getTotalSlots() != 0) {
                        getcampaid.add(CampController.getAllCamps().get(i).getCampId());
                    }
                }

                while (continues) {
                    System.out.println("\n1) As committee\n2) As attendee\n3) No");
                    // note: if 0 will error withdraw
                    Integer reg = CommonUse.dataValidation();
                    switch (reg) {
                        case 1: // reg as committee
                            // note: check if alr in another camp, rej if yes

                            System.out.println("Select the camp you want to join");

                            Integer input = CommonUse.dataValidation();
                            for (int i = 0; i < getcampcid.size(); i++) {
                                if (CampController.checkCampCommittee(CampController.getAllCamps().get(i).getCampId(),
                                        loggedID)) {
                                    System.out.println("You are already in another camp.");
                                    continues = false;
                                    break;
                                } else if (input == i) {
                                    CampController.addCommittee(CampController.getAllCamps().get(i).getCampId(),
                                            loggedID);
                                    System.out.println("You have successfully registered as a committee");
                                }

                            }

                            continues = false;
                            break;

                        case 2: // reg as attendee
                            System.out.println("Select the camp you want to join");
                            input = CommonUse.dataValidation();

                            for (int i = 0; i < getcampaid.size(); i++) {

                                if (input == i) {
                                    CampController.addParticipant(CampController.getAllCamps().get(i).getCampId(),
                                            loggedID);
                                    System.out.println("You have successfully registered as an attendee");
                                }

                            }

                            continues = false;
                            break;

                        case 3:
                            continues = false;
                            break;

                        default:
                            System.out.println("Invalid");
                            break;
                    }
                }
                continues = true;
                break;

            case 3: // submit enquiries
                ArrayList<String> getcampid = new ArrayList<>();
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    getcampid.add(CampController.getAllCamps().get(i).getCampId());
                }
                Integer input = CommonUse.dataValidation();
                if (input >= getcampid.size()) {
                    System.out.println("No such camp");
                } else {
                    for (int j = 0; j < getcampid.size(); j++) {
                        Scanner scan = new Scanner(System.in);
                        String enquire = "";
                        System.out.println("Please input your enquiry:");
                        enquire += scan.nextLine();
                        EnquiryController.createEnquiry(getcampid.get(j), loggedID, enquire);

                    }
                }
                break;

            case 4: // view/edit/del enquiries b4 it's processed

                while (continues) {
                    ArrayList<String> getenquirymsg = new ArrayList<>();
                    ArrayList<String> getenquiryid = new ArrayList<>();

                    for (int i = 0; i < EnquiryController.getAllEnquiries().size(); i++) {
                        if (EnquiryController.getAllEnquiries().get(i).getStatus() == EnquiryStatus.PENDING) {
                            getenquiryid.add(EnquiryController.getAllEnquiries().get(i).getEnquiryId());
                            getenquirymsg.add(EnquiryController.getAllEnquiries().get(i).getMessage());

                        }

                    }
                    System.out.println("1) View\n2) Edit\n3) Delete\n4) Quit");

                    Integer edit = CommonUse.dataValidation();
                    switch (edit) {
                        case 1: // view enquiry
                            System.out.println("*********Your enquiries*********");
                            for (int i = 0; i < getenquirymsg.size(); i++) {

                                System.out.println(i + ") " + getenquirymsg.get(i));

                            }
                            System.out.println("********************************");
                            break;
                        case 2: // edit enquiry
                            Scanner scan = new Scanner(System.in);
                            System.out.println("Choose the enquiries you want to edit");

                            input = CommonUse.dataValidation();
                            if (input >= getenquiryid.size())
                                System.out.println("No such enquiry");
                            else {
                                for (int j = 0; j < getenquiryid.size(); j++) {
                                    if (input == j) {
                                        String updateenquirymsg = "";
                                        updateenquirymsg += scan.nextLine();
                                        EnquiryController.updateEnquiryMessage(getenquiryid.get(j), updateenquirymsg);
                                        System.out.println("Enquiry updated");
                                    }

                                }
                            }

                            break;

                        case 3: // del enquiry
                            System.out.println("Choose the enquiry you want to delete:");
                            input = CommonUse.dataValidation();
                            if (input >= getenquiryid.size())
                                System.out.println("No such enquiry");
                            else {
                                for (int j = 0; j < getenquiryid.size(); j++) {
                                    if (input == j) {
                                        EnquiryController.deleteEnquiry(getenquiryid.get(j));
                                        System.out.println("Enquiry deleted");
                                    }
                                }

                            }

                            break;

                        case 4: // quit
                            continues = false;
                            break;

                        default:
                            System.out.println("Invalid");
                            break;
                    }
                }
                continues = true;

                break;

            case 5: // view registered camps n role(attendee/committee)
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    getcampaid.add(CampController.getAllCamps().get(i).getCampId());
                    System.out.println(getcampaid.size());
                }
                System.out.println(
                        "These are the camps you have registered for");

                break;

            case 6: // withdraw from camp
                // note: update remaining slots n not allowed to reg for the same camp they
                // withdrew from
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    if (CampController.checkCampParticipant(CampController.getAllCamps().get(i).getCampId(),
                            loggedID)) {
                        getcampaid.add(CampController.getAllCamps().get(i).getCampId());
                    }
                }
                System.out.println("Select the camp you want to withdraw from");

                input = CommonUse.dataValidation();
                if (input >= getcampaid.size()) {
                    System.out.println("No such camp");
                } else {
                    for (int j = 0; j < getcampaid.size(); j++) {
                        if (input == j && CampController.checkCampParticipant(getcampaid.get(j), loggedID)) {
                            CampController.removeParticipant(getcampaid.get(j), loggedID);
                        }
                    }
                    System.out.println("You have successfully withdrawn from the camp");
                }

                break;

            case 7: // view reply
                common.ViewReply();
                break;
            case 8: // change
                Scanner scan = new Scanner(System.in);
                String pw = scan.nextLine();
                UserController.changePassword(loggedID, pw);
                System.out.println("Password updated");
                break;

            case 9: // profile

                break;

            default:
                break;
        }
        // }

    }
}