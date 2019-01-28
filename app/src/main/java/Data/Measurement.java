package Data;

import utils.MyDateUtils;

public class Measurement {
    private String city;
    private String countryCode;
    private String location;
    private String date;
    private String param;
    private double value;
    private String unit;
    private double longitude;
    private double latitude;

    public Measurement(String inputCountryCode,
                       String inputCity,
                       String inputLocation,
                       String inputDate,
                       String inputParam,
                       double inputValue,
                       String inputUnit,
                       double inputLong,
                       double inputLat
                    )
    {
        location = inputLocation;
        city = inputCity;
        countryCode = inputCountryCode;
        date = MyDateUtils.reformatDate(inputDate);
        param = inputParam;
        value = inputValue;
        unit = inputUnit;
        longitude = inputLong;
        latitude = inputLat;
    }

    public String getCity() {return city;}

    public String getCountryCode() {return countryCode;   }

    public String getLocation() { return location;    }

    public String getDate() {return date;    }

    public String getParam() { return param;    }

    public double getValue() { return value;    }

    public String getUnit() {  return unit;    }

    public double getLongitude() { return longitude;    }

    public double getLatitude() { return latitude;    }
}
