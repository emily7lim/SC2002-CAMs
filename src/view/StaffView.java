package view;

import controller.*;
import model.Camp;
import model.enums.EnquiryStatus;
import model.enums.SuggestionStatus;
import report.enums.ReportType;

import java.util.*;

public class StaffView {

    public static void Staff(Integer choice, String loggedID) {
        Scanner sc = new Scanner(System.in);
        CommonUse common = new CommonUse();
        
        ArrayList<Camp> camps = new ArrayList<>();
        
        boolean continues = true;

        switch (choice) {
            case 1: // view all camps n list of students
                System.out.println("1) View all camps\n2) View list of students");
                Integer view = CommonUse.dataValidation();

                switch (view) {
                    case 1:
                        common.ViewingCamps(loggedID);
                        break;
                    case 2:
                        ArrayList<String> getparticipant = new ArrayList<>();
                        ArrayList<String> getcommittee = new ArrayList<>();
                        for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                            getparticipant.addAll(CampController.getAllCamps().get(i).getParticipantIds());
                            getcommittee.addAll(CampController.getAllCamps().get(i).getCommitteeIds());
                        }
                        System.out.println("********Committee********");
                        for (int i = 0; i < getcommittee.size(); i++) {
                            System.out.println(i + ") " + getcommittee.get(i));
                        }
                        System.out.println("********Participant********");
                        for (int i = 0; i < getparticipant.size(); i++) {
                          System.out.println(i + ")" + getparticipant.get(i));
                        }
                        break;
                    
                    default:
                        break;
                }

                break;

            case 2: // see list of camps that he/she created in separate menu list so they can edit
                StaffCreated.StaffCreate(loggedID);
                break;

            case 3: // profile

                break;

            case 4: // view&reply enquiries from students to the camps the staff created

                ArrayList<String> getenquirymsg = new ArrayList<>();
                ArrayList<String> getenquiryid = new ArrayList<>();

                for (int i = 0; i < EnquiryController.getAllEnquiries().size(); i++) {
                    if(EnquiryController.getAllEnquiries().get(i).getStatus() == EnquiryStatus.PENDING){
                    getenquiryid.add(EnquiryController.getAllEnquiries().get(i).getEnquiryId());
                    getenquirymsg.add(EnquiryController.getAllEnquiries().get(i).getMessage());
                    }
                }
                System.out.println("*********Your enquiries***********");
                for (int i = 0; i < getenquirymsg.size(); i++) {

                    System.out.println(i + ") " + getenquirymsg.get(i));

                }
                System.out.println("**********************************");
                while (continues) {

                    System.out.println("1) Reply Enquiries\n2) Generate report\n3) Quit");
                    Scanner scan = new Scanner(System.in);
                    Integer reply = CommonUse.dataValidation();

                    switch (reply) {
                        case 1:
                            System.out.println("Select enquiry to reply");
                            sc = new Scanner(System.in);
                            Integer input = CommonUse.dataValidation();

                            if (input >= getenquirymsg.size()) {
                                System.out.println("No such enquiry");
                            } else {
                                for (int i = 0; i < getenquiryid.size(); i++) {
                                    if (input == i) {
                                        System.out.println("Input your enquiry");
                                        String replyenquiry = "";
                                        replyenquiry += scan.nextLine();
                                        EnquiryController.replyEnquiry(getenquiryid.get(i), replyenquiry, loggedID);
                                    }
                                }
                            }

                            break;

                        case 2:
                            for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                                if (CampController.getAllCamps().get(i).getStaffInCharge().equals(loggedID)) {
                                    camps.add(CampController.getCampById(CampController.getAllCamps().get(i).getCampId()));
                                }
                            }
                            CommonUse.FileType(camps, null, ReportType.ENQUIRIES_REPORT);
                            continues = true;
                            break;

                        case 3:
                            continues = false;
                            break;

                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }
                continues = true;
                break;

            case 5: // generate report of list of students attending each camp the staff created.
                    // list will include details of camp n roles of participants
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    if (CampController.getAllCamps().get(i).getStaffInCharge().equals(loggedID)) {
                        camps.add(CampController.getCampById(CampController.getAllCamps().get(i).getCampId()));
                    }
                }
                CommonUse.FilterReport(camps);
                continues = true;
                break;

            case 6: // performance report of camp comm members
                
                for (int i = 0; i < CampController.getAllCamps().size(); i++) {
                    if (CampController.getAllCamps().get(i).getStaffInCharge().equals(loggedID)) {
                    camps.add(CampController.getCampById(CampController.getAllCamps().get(i).getCampId()));
                    }
                }

                CommonUse.FileType(camps, null, ReportType.PERFORMANCE_REPORT);                
                continues = true;
                break;

            case 7: // view&approve suggestions to changes to camp details from camp comm
                ArrayList<String> getsuggestmsg = new ArrayList<>();
                ArrayList<String> getsuggestid = new ArrayList<>();

                for (int i = 0; i < SuggestionController.getAllSuggestions().size(); i++) {
                    if (SuggestionController.getAllSuggestions().get(i).getStatus() == SuggestionStatus.PENDING) {
                        getsuggestid.add(SuggestionController.getAllSuggestions().get(i).getSuggestionId());
                        getsuggestmsg.add(SuggestionController.getAllSuggestions().get(i).getMessage());
                    }

                }
                System.out.println("*********Suggestion from camp committee*********");
                for (int i = 0; i < getsuggestmsg.size(); i++) {

                    System.out.println(i + ") " + getsuggestmsg.get(i));

                }
                System.out.println("************************************************");

                while (continues) {
                    System.out.println("1) Approve suggestion\n2) Reject suggestion\n3) Quit");
                    Integer input = CommonUse.dataValidation();

                    switch (input) {
                        case 1:
                            System.out.println("Select the suggestion you want to approve:");
                            
                            Integer changestatus = CommonUse.dataValidation();
                            if (changestatus >= getsuggestid.size()) {
                                System.out.println("No such suggestion");
                            } else {
                                for (int i = 0; i < getsuggestid.size(); i++) {
                                    if (changestatus == i) {

                                        SuggestionController.acceptSuggestion(getsuggestid.get(i), loggedID);
                                        System.out.println("This suggestions has been accepted");
                                    }
                                }
                            }

                            break;

                        case 2:
                            System.out.println("Select the suggestion you want to reject:");
                            
                            changestatus = CommonUse.dataValidation();
                            if (changestatus >= getsuggestid.size()) {
                                System.out.println("No such suggestion");
                            } else {
                                for (int i = 0; i < getsuggestid.size(); i++) {
                                    if (changestatus == i) {

                                        SuggestionController.rejectSuggestion(getsuggestid.get(i), loggedID);
                                        System.out.println("This suggestions has been rejected");
                                    }
                                }
                            }

                            break;
                        case 3:
                            continues = false;
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                }
                continues = true;
                break;

            case 8: // view replies
                common.Result();
                break;

            case 9: // change password
                System.out.println("Input your new password");
                Scanner scan = new Scanner(System.in);
                String pw = scan.nextLine();
                UserController.changePassword(loggedID, pw);
                System.out.println("Password updated");
                break;

            case 10:
                continues = false;
                break;

            default:
                System.out.println("Invalid option");
                break;
        }
        // }

    }
}