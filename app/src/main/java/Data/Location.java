package Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.MyDateUtils;

public class Location {
    private String city;
    private String countryCode;
    private String location;
    private String date;


    public Location(
            String inputCountryCode,
            String inputCity,
            String inputLocation,
            String inputDate)
    {
        location = inputLocation;
        city = inputCity;
        countryCode = inputCountryCode;
        date = MyDateUtils.reformatDate(inputDate);
    }



    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
