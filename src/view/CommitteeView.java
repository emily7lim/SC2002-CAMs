package view;

import java.util.Scanner;

public class CommitteeView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean stop = true;
        while (stop) {
            System.out.println("1) View details of camp registered\n2)Submit suggestions\n3) Enquiries \n4) View,edit,delete suggestions\n5) Generate student list report\n");
            Integer choice = sc.nextInt();
            switch (choice) {
                case 1:
                    
                    break;
            
                case 2:
                    
                    break;
            
                case 3:
                    
                    break;
            
                case 4:
                    
                    break;
            
                case 5:
                    stop = false;
                    break;
            
                default:
                    System.out.println("Invalid");
                    break;
            }
            
        }
    }
}
