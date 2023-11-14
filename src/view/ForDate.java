package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForDate {
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