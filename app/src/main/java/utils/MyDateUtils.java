package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class MyDateUtils {
    //method used to covert data to required format

    static private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static private SimpleDateFormat outputDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static String reformatDate(String inputDate)
    {
        String output = null;
        try {
            String cutString = inputDate.substring(0,10);
            Date temp = inputDateFormat.parse(cutString);
            output = outputDateFormat.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }
}
