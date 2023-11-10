package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;
import model.enums.EnquiryStatus;

public class StudentView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;
        EnquiryController enquiryc = new EnquiryController();
        
        String loggedID = "YCHERN";  // note:take from login detail
        while (continues) {

            System.out.println("------------------Student Menu------------------\n1) View list of camp\n2) Remaining slots of camps\n3) Submit enquiries\n4) View/edit/del enquiries\n5) View registered camps\n6) Withdraw from camp \n7) Quit");
            System.out.println("------------------------------------------------\nSelect your choice:");

            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // view list of camps
                    break;

                case 2: // view remaining camp slots
                    //note: show reminaing slots here
                    while (continues) {
                        System.out.println("Do you want to register for camp?\n1) Yes, as committee\n2) Yes, as attendee\n3) No");
                        Integer reg = sc.nextInt();
                        switch (reg) {
                            case 1: //reg as committee
                                //note: check if alr in another camp, rej if yes
                                continues = false;
                                break;
                        
                            case 2: //reg as attendee
                                System.out.println("You have successfully registered as an attendee");
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
                    Scanner scan = new Scanner(System.in);
                    String enquire = "";
                    System.out.println("Please input your enquiry:");
                    enquire += scan.nextLine();
                    enquiryc.createEnquiry(loggedID, enquire);
                    break;

                case 4: // view/edit/del enquiries b4 it's processed
                    
                    while (continues) {
                        ArrayList<String> getenquirymsg = new ArrayList<>();
                        ArrayList<String> getenquiryid = new ArrayList<>();

                        for (int i = 0; i < enquiryc.getAllEnquiries().size(); i++) {
                            if (enquiryc.getAllEnquiries().get(i).getStatus() == EnquiryStatus.PENDING) {
                                getenquiryid.add(enquiryc.getAllEnquiries().get(i).getEnquiryId());
                                getenquirymsg.add(enquiryc.getAllEnquiries().get(i).getMessage());

                            } else
                                continue;

                        }
                        System.out.println("1) View\n2) Edit\n3) Delete\n4) Quit");
                        Integer edit = sc.nextInt();
                        switch (edit) {
                            case 1: //view enquiry
                                System.out.println("*********Your enquiries*********");
                                for (int i = 0; i < getenquirymsg.size(); i++) {

                                    System.out.println(i + ") " + getenquirymsg.get(i));

                                }
                                System.out.println("**********************************");
                                break;
                            case 2: //edit enquiry
                                scan = new Scanner(System.in);
                                System.out.println("Choose the enquiries you want to edit");
                                Integer input = sc.nextInt();
                                if (input >= getenquiryid.size())
                                    System.out.println("No such enquiry");
                                else {
                                    for (int j = 0; j < getenquiryid.size(); j++) {
                                        if (input == j) {
                                            String updateenquirymsg = "";
                                            updateenquirymsg += scan.nextLine();
                                            enquiryc.updateEnquiryMessage(getenquiryid.get(j),updateenquirymsg);
                                            System.out.println("Enquiry updated");
                                        }

                                    }
                                }

                                break;

                            case 3: //del enquiry
                                System.out.println("Choose the enquiry you want to delete:");
                                input = sc.nextInt();
                                if (input >= getenquiryid.size())
                                    System.out.println("No such enquiry");
                                else {
                                    for (int j = 0; j < getenquiryid.size(); j++) {
                                        if (input == j) {
                                            enquiryc.deleteEnquiry(getenquiryid.get(j));
                                            System.out.println("Enquiry deleted");
                                        }
                                    }

                                }

                                break;

                            case 4: //quit
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
                        
                    System.out.println(
                            "These are the camps you have registered for");
                    break;

                case 6: // withdraw from camp
                    //note: update remaining slots n not allowed to reg for the same camp they withdrew from
                    break;

                case 7: 
                    continues = false;
                    break;

                default:
                    System.out.println("Invalid, please try again");
                    break;
            }
        }

    }
}