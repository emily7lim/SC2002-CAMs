package utils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HelperUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
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
