package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ForDate Class to handle Date formatting
 * 
 * @author Emily
 * @version 1.2.1
 * @since 2023-11-11
 */
public class ForDate {
    /**
     * Formats a String to a Date
     * 
     * @param dateString The Date String to be formatted
     * @return Date The formatted Date from the String
     */
    public static Date getDates(String dateString) {
        Date date = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            date = format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Incorrect date format. Please try again:");
        }

        return date;
    }
}