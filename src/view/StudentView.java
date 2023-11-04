package view;

import java.util.Scanner;

public class StudentView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stop = true;
        while (stop) {

            System.out.println("------------------Student Menu------------------\n1) View list of camp\n2) Remaining slots of camps\n3) Submit enquiries\n4) View/edit/del enquiries\n5) View registered camps\n6) Withdraw from camp \n7) Quit");
            System.out.println("------------------------------------------------\nSelect your choice:");

            Integer choice = sc.nextInt();
            switch (choice) {
                case 1: // view list of camps
                    break;

                case 2: // view remaining camp slots
                    //note: show reminaing slots here
                    while (stop) {
                        System.out.println("Do you want to register for camp?\n1) Yes, as committee\n2) Yes, as attendee\n3) No");
                        Integer reg = sc.nextInt();
                        switch (reg) {
                            case 1: //reg as committee
                                //note: check if alr in another camp, rej if yes
                                stop = false;
                                break;
                        
                            case 2: //reg as attendee
                                System.out.println("You have successfully registered as an attendee");
                                stop = false;
                                break;
                        
                            case 3:
                                stop = false;
                                break;
                        
                            default:
                                System.out.println("Invalid");
                                break;
                        }
                    }
                    stop = true;
                    break;

                case 3: // submit enquiries
                    
                    break;

                case 4: // view/edit/del enquiries b4 it's processed
                    //note: if student has multiple enquiries, do for loop n let them choose which to view? list all 1st?
                    System.out.println("Choose what you want to do with the enquiries");
                    while (stop) {
                        System.out.println("1) View \n2) Edit\n3) Delete\n4) Quit");
                        Integer enq = sc.nextInt();
                        switch (enq) {
                            case 1:
                                
                                break;
                        
                            case 2:
                                System.out.println("Which do you want to edit?");
                                Integer edit = sc.nextInt();
                                //note: num of enquiries they have
                                for (int i = 0; i < args.length; i++) {
                                    // if(edit == i+1) edit
                                }
                                break;
                        
                            case 3: // del
                                System.out.println("Which do you want to delete?");
                                Integer del = sc.nextInt();
                                //note: num of enquiries they have
                                for (int i = 0; i < args.length; i++) {
                                    // if(del == i+1) delete
                                }
                                break;
                        
                            case 4:
                                stop = false;
                                break;
                        
                            default:
                                System.out.println("Invalid..");
                                break;
                        }
                    }
                    stop = true;
                    break;

                case 5: // view registered camps n role(attendee/committee)
                        
                    System.out.println(
                            "These are the camps you have registered for");
                    break;

                case 6: // withdraw from camp
                    //note: update remaining slots n not allowed to reg for the same camp they withdrew from
                    break;

                case 7: 
                    stop = false;
                    break;

                default:
                    System.out.println("Invalid, please try again");
                    break;
            }
        }

    }
}