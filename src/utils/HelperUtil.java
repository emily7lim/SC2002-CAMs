package utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HelperUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[0;0H\033[2J");
                System.out.flush();
            }
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    public static void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        try {
            System.in.read();
            sc.nextLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * @param min
     * @param max
     * @return int
     */
    public static int nextInt(int min, int max) {
        try {
            int input = sc.nextInt();
            sc.nextLine();

            if (input < min || input > max)
                System.out.println("Invalid input, option is out of range.");
            else
                return input;
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input, enter an integer.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static int nextInt(int min) {
        try {
            int input = sc.nextInt();
            sc.nextLine();

            if (input < min)
                System.out.println("Invalid input, please try again.");
            else
                return input;
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid input, enter an integer.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static String nextString() {
        String input = sc.nextLine();
        return input.trim();
    }
}
