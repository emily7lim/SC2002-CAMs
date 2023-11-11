package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForDate {
    public Date getDates(String dateString) throws ParseException {
        Date date = null;
        // Boolean continues = true;
        // while (continues) {
            try {

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                date = format.parse(dateString);

                // continues = false;
                
            } catch (ParseException e) {

                System.out.println("Incorrect date format. Please try again:");
                
            }
       
        // } 
        return date;
    }
}