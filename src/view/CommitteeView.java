package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;
import model.enums.SuggestionStatus;

public class CommitteeView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;

        SuggestionController suggestc = new SuggestionController();
        StudentView student = new StudentView();
        EnquiryController enquiryc = new EnquiryController();
        StudentController studentc = new StudentController();

        String loggedID = "YCHERN"; // note:take from login detail
        while (continues) {
            System.out.println(
                    "------------------Committee Menu------------------\n1) View list of camp\n2) Register for camps\n3) Submit enquiries\n4) View/edit/del enquiries\n5) View registered camps\n6) Withdraw from camp \n7) View Replies\n8) Change password\n9) Profile\n10) Submit suggestions\n11) Enquiries from student \n12) View,edit,delete suggestions\n13) Generate student list report\n14) Quit");
            System.out.println("--------------------------------------------------\nSelect your choice:");
            Integer choice = sc.nextInt();
            if (choice < 10 && choice > 0) {
                student.Students(choice);
            } else {
                switch (choice) {

                    case 10: // submit suggestions
                        Scanner scan = new Scanner(System.in);
                        String suggest = "";

                        System.out.println("Please input your suggestion:");
                        suggest += scan.nextLine();
                        suggestc.createSuggestion(loggedID, suggest);
                        break;

                    case 11: // view n reply enquiries

                        ArrayList<String> getenquirymsg = new ArrayList<>();
                        ArrayList<String> getenquiryid = new ArrayList<>();

                        for (int i = 0; i < enquiryc.getAllEnquiries().size(); i++) {
                            getenquiryid.add(enquiryc.getAllEnquiries().get(i).getEnquiryId());
                            getenquirymsg.add(enquiryc.getAllEnquiries().get(i).getMessage());
                        }
                        System.out.println("*********Your enquiries*********");
                        for (int i = 0; i < getenquirymsg.size(); i++) {

                            System.out.println(i + ") " + getenquirymsg.get(i));

                        }
                        System.out.println("**********************************");
                        while (continues) {

                            System.out.println("1) Reply Enquiries\n2) Quit");
                            scan = new Scanner(System.in);
                            Integer reply = sc.nextInt();
                            switch (reply) {
                                case 1:
                                    sc = new Scanner(System.in);
                                    Integer input = sc.nextInt();
                                    if (input >= getenquirymsg.size()) {
                                        System.out.println("No such enquiry");
                                    } else {
                                        for (int i = 0; i < getenquiryid.size(); i++) {
                                            if (input == i) {
                                                String replyenquiry = "";
                                                replyenquiry += scan.nextLine();
                                                enquiryc.replyEnquiry(getenquiryid.get(i), replyenquiry, loggedID);
                                                studentc.addPoint(loggedID);
                                                System.out.println("You have replied 1 enquiry! +1 points");
                                            }
                                        }
                                    }

                                    break;

                                case 2:
                                    continues = false;
                                    break;

                                default:
                                    System.out.println("Invalid input");
                                    break;
                            }
                        }
                        continues = true;
                        break;

                    case 12: // view,edit,del details of suggestions b4 being processed

                        while (continues) {
                            ArrayList<String> getsuggestionmsg = new ArrayList<>();
                            ArrayList<String> getsuggestionid = new ArrayList<>();

                            for (int i = 0; i < suggestc.getAllSuggestions().size(); i++) {
                                if (suggestc.getAllSuggestions().get(i).getStatus() == SuggestionStatus.PENDING) {
                                    getsuggestionid.add(suggestc.getAllSuggestions().get(i).getSuggestionId());
                                    getsuggestionmsg.add(suggestc.getAllSuggestions().get(i).getMessage());

                                }

                            }
                            System.out.println("1) View\n2) Edit\n3) Delete\n4) Quit");
                            Integer edit = sc.nextInt();
                            switch (edit) {
                                case 1: // view suggestion
                                    System.out.println("*********Your suggestions*********");
                                    for (int i = 0; i < getsuggestionmsg.size(); i++) {

                                        System.out.println(i + ") " + getsuggestionmsg.get(i));

                                    }
                                    System.out.println("**********************************");
                                    break;
                                case 2: // edit suggestion
                                    scan = new Scanner(System.in);
                                    System.out.println("Choose the suggestions you want to edit");
                                    Integer input = sc.nextInt();
                                    if (input >= getsuggestionid.size())
                                        System.out.println("No such suggestion");
                                    else {
                                        for (int j = 0; j < getsuggestionid.size(); j++) {
                                            if (input == j) {
                                                String updatesuggestmsg = "";
                                                updatesuggestmsg += scan.nextLine();
                                                suggestc.updateSuggestionMessage(getsuggestionid.get(j),
                                                        updatesuggestmsg);
                                                System.out.println("Suggestion updated");
                                            }

                                        }
                                    }

                                    break;

                                case 3: // del suggestion
                                    System.out.println("Choose the suggestion you want to delete:");
                                    input = sc.nextInt();
                                    if (input >= getsuggestionid.size())
                                        System.out.println("No such suggestion");
                                    else {
                                        for (int j = 0; j < getsuggestionid.size(); j++) {
                                            if (input == j) {
                                                suggestc.deleteSuggestion(getsuggestionid.get(j));
                                                System.out.println("Suggestion deleted");
                                            }
                                        }

                                    }

                                    break;

                                case 4:
                                    continues = false;
                                    break;

                                default:
                                    break;
                            }
                        }
                        continues = true;

                        break;

                    case 13: // generate report of list of students attending the camp they oversee

                        break;

                    case 14:
                        continues = false;
                        break;

                    default:
                        System.out.println("Invalid...");
                        break;
                }
            }
        }
    }
}
