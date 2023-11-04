package view;

import controller.CampController;
import model.Camp;

import java.util.Scanner;

public class StaffView {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stop = true;
        while (stop) {

            System.out.println("------------------Staff Menu------------------\n1) View all camps\n2) See list of camps I created\n3) Toggle visibility of camp\n4) Enquiries from student\n5) Generate report of students\n6) Generate performance report of committee members\n7) Suggestions from camp committee\n8) Create,edit,delete camps\n9)Quit");
            System.out.println("----------------------------------------------\nSelect your choice:");

            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // view all camps
                    CampController cc = new CampController();
                    System.out.println(cc.getAllCamps());
                    break;

                case 2: // see list of camps that he/she created in separate menu list so they can edit camp they created
                    StaffCreated.main(null);
                    break;

                case 3: // toggle visibility of camp, reflected in camp list that will be visible to students
                        
                    break;

                case 4: // view&reply enquiries from students to the camps the staff created

                    break;

                case 5: // generate report of list of students attending each camp the staff created. list will include details of camp n roles of participants
                    System.out.println("\nSelect what you want to be generated \n1) Attendee\n2) Camp committee\n3) Roles \n5) Quit");
                    System.out.println("Enter your choice and select 5 to quit");
                    // note: check for duplicate also
                    stop = true;
                    while (stop) {
                        int filter = sc.nextInt();
                        switch (filter) {
                            case 1:

                                break;

                            case 2:

                                break;

                            case 3:

                                break;

                            case 5:
                                stop = false;
                                break;

                            default:
                                System.out.println("Invalid detail");
                                break;
                        }

                    }
                    // note: show the details they want??
                    System.out.println("\nSelect your format\n1) .txt \n2) .csv");
                    stop = true;
                    while (stop) {
                        int format = sc.nextInt();
                        switch (format) {
                            case 1: // txt

                                stop = false;
                                break;

                            case 2: // csv

                                stop = false;
                                break;

                            default:
                                System.out.print("Please select file format 1 or 2: ");
                                break;
                        }
                    }

                    stop = true;
                    break;

                case 6: // performance report of camp comm members
                    break;

                case 7: // view&approve suggestions to changes to camp details from camp comm
                    break;

                case 8: //create, edit,del camps
                    while (stop) {
                        System.out.println("1) Create\n2) Edit\n3) Delete\n4) Quit");
                        Integer edit = sc.nextInt();
                        switch (edit) {
                            case 1:
                                
                                break;
                        
                            case 2:
                                
                                break;
                        
                            case 3:
                                
                                break;
                        
                            case 4:
                                stop = false;
                                break;
                        
                            default:
                                System.out.println("Invalid!");
                                break;
                        }
                    }
                    stop = true;
                    break;

                case 9:
                    stop = false;
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }
}