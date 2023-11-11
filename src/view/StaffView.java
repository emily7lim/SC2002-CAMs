package view;

import controller.*;
import model.enums.EnquiryStatus;
import model.enums.Faculty;
import model.enums.SuggestionStatus;
import report.ReportController;
import report.enums.ReportOutputType;
import report.enums.ReportType;

// import java.io.IOException;
// import org.apache.commons.lang.WordUtils;
import java.text.ParseException;
import java.util.*;

public class StaffView {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String loggedID = "ARVI"; // note:take from login detail
        EnquiryController enquiryc = new EnquiryController();
        CampController campc = new CampController();
        StudentController studentc = new StudentController();
        SuggestionController suggestc = new SuggestionController();
        CommonUse common = new CommonUse();

        boolean continues = true;

        while (continues) {
            System.out.println(
                    "\n------------------Staff Menu------------------\n1) View all camps and students registered\n2) See list of camps I created\n3) Profile\n4) Enquiries from student\n5) Generate report of students\n6) Generate performance report of committee members\n7) Suggestions from camp committee\n8) Results of enquiries/suggestions\n9) Change password\n10) Quit");
            System.out.println("----------------------------------------------\nSelect your choice:");
            
            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // view all camps n list of students
                System.out.println("1) View all camps\n2) View list of students");
                    Integer view = sc.nextInt();
                    if (view == 1)
                    common.ViewingCamps();
                    else if (view==2) {
                        ArrayList<String> getparticipant = new ArrayList<>();
                        ArrayList<String> getcommittee = new ArrayList<>();
                        for (int i = 0; i < campc.getAllCamps().size(); i++) {
                            getparticipant.addAll(campc.getAllCamps().get(i).getParticipantIds());
                            getcommittee.addAll(campc.getAllCamps().get(i).getCommitteeIds());
                        }
                        for (int i = 0; i < getcommittee.size(); i++) {
                            System.out.println(i + ")" + getcommittee.get(i) + "Participant: " + getparticipant.get(i));
                        }
                    }
                    break;

                case 2: // see list of camps that he/she created in separate menu list so they can edit
                    StaffCreated.main(null);
                    break;

                case 3: // profile

                    break;

                case 4: // view&reply enquiries from students to the camps the staff created

                    ArrayList<String> getenquirymsg = new ArrayList<>();
                    ArrayList<String> getenquiryid = new ArrayList<>();

                    for (int i = 0; i < enquiryc.getAllEnquiries().size(); i++) {
                        getenquiryid.add(enquiryc.getAllEnquiries().get(i).getEnquiryId());
                        getenquirymsg.add(enquiryc.getAllEnquiries().get(i).getMessage());
                    }
                    System.out.println("*********Your enquiries***********");
                    for (int i = 0; i < getenquirymsg.size(); i++) {

                        System.out.println(i + ") " + getenquirymsg.get(i));

                    }
                    System.out.println("**********************************");
                    while (continues) {

                        System.out.println("1) Reply Enquiries\n2) Quit");
                        Scanner scan = new Scanner(System.in);
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

                case 5: // generate report of list of students attending each camp the staff created.
                        // list will include details of camp n roles of participants
                    System.out.println(
                            "\nSelect what you want to be generated \n1) Attendee\n2) Camp committee\n3) Roles \n5) Quit");
                    System.out.println("Enter your choice and select 5 to quit");
                    // note: check for duplicate also
                    continues = true;
                    while (continues) {
                        int filter = sc.nextInt();
                        switch (filter) {
                            case 1:

                                break;

                            case 2:

                                break;

                            case 3:

                                break;

                            case 5:
                                continues = false;
                                break;

                            default:
                                System.out.println("Invalid detail");
                                break;
                        }

                    }
                    // note: show the details they want??
                    System.out.println("\nSelect your format\n1) .txt \n2) .csv");
                    continues = true;

                    while (continues) {
                        int format = sc.nextInt();
                        switch (format) {
                            case 1: // txt
                                ReportController rp = new ReportController(ReportOutputType.TXT);
                                continues = false;
                                break;

                            case 2: // csv
                                rp = new ReportController(ReportOutputType.CSV);
                                continues = false;
                                break;

                            default:
                                System.out.print("Please select file format 1 or 2: ");
                                break;
                        }
                    }
                    // rp.generateAndWriteReports();
                    continues = true;
                    break;

                case 6: // performance report of camp comm members
                    ArrayList<String> getcomm = new ArrayList<>();
                    ArrayList<Integer> getpts = new ArrayList<>();
                        for (int i = 0; i < enquiryc.getAllEnquiries().size(); i++) {
                            getcomm.add("UserID" + studentc.getAllStudents().get(i).getUserId() + "Name: " + studentc.getAllStudents().get(i).getName() );
                            getpts.add(studentc.getAllStudents().get(i).getPoints() );
                        }
                     System.out.println("\nSelect your format\n1) .txt \n2) .csv");

                    while (continues) {
                        
                        int format = sc.nextInt();
                        switch (format) {
                            case 1: // txt
                                ReportController rp = new ReportController(ReportOutputType.TXT);
                                // rp.generateAndWriteReports(camps, ReportType.PERFORMANCE_REPORT);
                                continues = false;
                                break;

                            case 2: // csv
                                rp = new ReportController(ReportOutputType.CSV);
                                continues = false;
                                break;

                            default:
                                System.out.print("Please select file format 1 or 2: ");
                                break;
                        }
                    }
                    // rp.generateAndWriteReports();
                    continues = true;
                    break;

                case 7: // view&approve suggestions to changes to camp details from camp comm
                    ArrayList<String> getsuggestmsg = new ArrayList<>();
                    ArrayList<String> getsuggestid = new ArrayList<>();

                    for (int i = 0; i < suggestc.getAllSuggestions().size(); i++) {
                        if (suggestc.getAllSuggestions().get(i).getStatus() == SuggestionStatus.PENDING) {
                            getsuggestid.add(suggestc.getAllSuggestions().get(i).getSuggestionId());
                            getsuggestmsg.add(suggestc.getAllSuggestions().get(i).getMessage());
                        }

                    }
                    System.out.println("*********Suggestion from camp committee*********");
                    for (int i = 0; i < getsuggestmsg.size(); i++) {

                        System.out.println(i + ") " + getsuggestmsg.get(i));

                    }
                    System.out.println("************************************************");

                    while (continues) {
                        System.out.println("1) Approve suggestion\n2) Reject suggestion\n3) Quit");
                        Integer input = sc.nextInt();
                        switch (input) {
                            case 1:
                                System.out.println("Select the suggestion you want to approve:");
                                Integer changestatus = sc.nextInt();
                                if (changestatus >= getsuggestid.size()) {
                                    System.out.println("No such suggestion");
                                } else {
                                    for (int i = 0; i < getsuggestid.size(); i++) {
                                        if (changestatus == i) {

                                            suggestc.acceptSuggestion(getsuggestid.get(i), loggedID);
                                            System.out.println("This suggestions has been accepted");
                                        }
                                    }
                                }

                                break;

                            case 2:
                                System.out.println("Select the suggestion you want to reject:");
                                changestatus = sc.nextInt();
                                if (changestatus >= getsuggestid.size()) {
                                    System.out.println("No such suggestion");
                                } else {
                                    for (int i = 0; i < getsuggestid.size(); i++) {
                                        if (changestatus == i) {

                                            suggestc.rejectSuggestion(getsuggestid.get(i), loggedID);
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
                    
                    break;

                case 10:
                    continues = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }
}