package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.*;
import model.Camp;
import model.enums.SuggestionStatus;

public class CommitteeView {
    public static void Committee(Integer choice, String loggedID) {
        Scanner sc = new Scanner(System.in);
        boolean continues = true;

        if (choice < 10 && choice > 0) {
            if (choice == 6)
                System.out.println("You are not allowed to withdraw");
            else
                StudentView.Students(choice, loggedID);
        } else {
            switch (choice) {

                case 10: // submit suggestions
                    ArrayList<String> getcampid = new ArrayList<>();
                    for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                        getcampid.add(CampController.getAllCamps().get(i).getCampId());
                    }
                    System.out.println("Select the camp you want to make suggestions");
                    Integer input = CommonUse.dataValidation();
                    if (input >= getcampid.size()) {
                        System.out.println("No such camp");
                    } else {
                        for (int j = 0; j < getcampid.size(); j++) {
                            if (input == j) {
                                Scanner scan = new Scanner(System.in);
                                String suggest = "";

                                System.out.println("Please input your suggestion:");
                                suggest += scan.nextLine();
                                System.out.println(getcampid.get(j));
                                SuggestionController.createSuggestion(getcampid.get(j), loggedID, suggest);
                            }

                        }
                    }
                    break;

                case 11: // view n reply enquiries

                    ArrayList<String> getenquirymsg = new ArrayList<>();
                    ArrayList<String> getenquiryid = new ArrayList<>();

                    for (int i = 0; i < EnquiryController.getAllEnquiries().size(); i++) {
                        getenquiryid.add(EnquiryController.getAllEnquiries().get(i).getEnquiryId());
                        getenquirymsg.add(EnquiryController.getAllEnquiries().get(i).getMessage());
                    }
                    System.out.println("*********Your enquiries*********");
                    for (int i = 0; i < getenquirymsg.size(); i++) {

                        System.out.println(i + ") " + getenquirymsg.get(i));

                    }
                    System.out.println("**********************************");
                    while (continues) {

                        System.out.println("1) Reply Enquiries\n2) Quit");
                        Scanner scan = new Scanner(System.in);
                        Integer reply = CommonUse.dataValidation();
                        switch (reply) {
                            case 1:
                                System.out.println("Choose the enquiry you want to reply");
                                sc = new Scanner(System.in);
                                input = CommonUse.dataValidation();
                                if (input >= getenquirymsg.size()) {
                                    System.out.println("No such enquiry");
                                } else {
                                    for (int i = 0; i < getenquiryid.size(); i++) {
                                        if (input == i) {
                                            String replyenquiry = "";
                                            replyenquiry += scan.nextLine();
                                            EnquiryController.replyEnquiry(getenquiryid.get(i), replyenquiry, loggedID);
                                            StudentController.addPoint(loggedID);
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

                        for (int i = 0; i < SuggestionController.getAllSuggestions().size(); i++) {
                            if (SuggestionController.getAllSuggestions().get(i)
                                    .getStatus() == SuggestionStatus.PENDING) {
                                getsuggestionid.add(SuggestionController.getAllSuggestions().get(i).getSuggestionId());
                                getsuggestionmsg.add(SuggestionController.getAllSuggestions().get(i).getMessage());

                            }

                        }
                        System.out.println("1) View\n2) Edit\n3) Delete\n4) Quit");
                        Integer edit = CommonUse.dataValidation();

                        switch (edit) {
                            case 1: // view suggestion
                                System.out.println("*********Your suggestions*********");
                                for (int i = 0; i < getsuggestionmsg.size(); i++) {

                                    System.out.println(i + ") " + getsuggestionmsg.get(i));

                                }
                                System.out.println("**********************************");
                                break;
                            case 2: // edit suggestion
                                Scanner scan = new Scanner(System.in);
                                System.out.println("Choose the suggestions you want to edit");

                                input = CommonUse.dataValidation();
                                if (input >= getsuggestionid.size())
                                    System.out.println("No such suggestion");
                                else {
                                    for (int j = 0; j < getsuggestionid.size(); j++) {
                                        if (input == j) {
                                            String updatesuggestmsg = "";
                                            updatesuggestmsg += scan.nextLine();
                                            SuggestionController.updateSuggestionMessage(getsuggestionid.get(j),
                                                    updatesuggestmsg);
                                            System.out.println("Suggestion updated");
                                        }

                                    }
                                }

                                break;

                            case 3: // del suggestion
                                System.out.println("Choose the suggestion you want to delete:");

                                input = CommonUse.dataValidation();
                                if (input >= getsuggestionid.size())
                                    System.out.println("No such suggestion");
                                else {
                                    for (int j = 0; j < getsuggestionid.size(); j++) {
                                        if (input == j) {
                                            SuggestionController.deleteSuggestion(getsuggestionid.get(j));
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
                    // loggedID = null;
                    continues = false;
                    break;

                default:
                    System.out.println("Invalid...");
                    break;
            }
        }
        // }
    }
}
