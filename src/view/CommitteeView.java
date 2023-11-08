package view;

import java.util.Scanner;

public class CommitteeView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stop = true;
        while (stop) {
            System.out.println("------------------Committee Menu------------------\n1) View details of camp registered\n2) Submit suggestions\n3) Enquiries \n4) View,edit,delete suggestions\n5) Generate student list report\n6) Points\n7) Quit");
            System.out.println("--------------------------------------------------\nSelect your choice:");
            Integer choice = sc.nextInt();
            switch (choice) {
                case 1:  //view details of camp that they have reg for
                    
                    break;
            
                case 2: //submit suggestions
                    
                    break;
            
                case 3: //view n reply enquiries
                    
                    break;
            
                case 4: //view,edit,del details of suggestions
                    
                    break;
            
                case 5: //generate report of list of students attending the camp they oversee
                    
                    break;
            
                case 6: //points from replying enquiries
                    
                    break;
            
                case 7:
                    stop = false;
                    break;
            
                default:
                    System.out.println("Invalid");
                    break;
            }
            
        }
    }
}
