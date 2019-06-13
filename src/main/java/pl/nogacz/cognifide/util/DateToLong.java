package pl.nogacz.cognifide.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Dawid Nogacz on 13.06.2019
 */
@Component
public class DateToLong {
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-DD-MM");;

    public Long convert(String string) {
       if(!string.matches("\\d{4}-\\d{2}-\\d{2}")) {
           if(string.matches("\\d{4}-\\d{2}")) {
               string = string + "-01";
           } else {
               string = string + "-01-01";
           }
        }

        return convertDate(string);
    }

    private Long convertDate(String string) {
        Long returnDate = null;

        try {
            Date date = dateFormat.parse(string);
            returnDate = date.getTime() / 1000;
        } catch(Exception e) {
            System.out.println("[DateToLong] " + e.getMessage());
        }

        return returnDate;
    }
}
