package utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * HelperUtil class to store support functions for other classes
 * @author Emily, Chloie
 * @version 1.1.3
 * @since 2023-1-19
 */
public class HelperUtil {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Clear viewable terminal
     */
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

    /**
     * Require user to press any key to continue using the applicatio
     */
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
     * Read integer from user input and validate if it is within range
     * @param min The lowest value the user can input
     * @param max The highest value the user can input
     * @return int The user input, -1 if invalid
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

    /** 
     * Read integer from user input and validate if it is within range
     * @param min The lowest value the user can input
     * @return int The user input, -1 if invalid
     */
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

    /**
     * Read and trim String from user input
     * @return String The trimmed user input
     */
    public static String nextString() {
        String input = sc.nextLine();
        return input.trim();
    }

    /**
     * Validate the password using a regex
     * @param password The password to be validated
     * @return String The password, "" if invalid
     */
    public static String validatePassword(String password) {
        if (password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"))
            return password;
        else {
            System.out.println("Invalid password, please enter a password with at least 8 characters \ncontaining at least one lowercase, uppercase, number and special character.\n");
            return "";
        }

    }
}
