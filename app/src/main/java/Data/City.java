package Data;

public class City {
    private String city;
    private String countryCode;

    public City(String inputCity, String inputCountryCode)
    {
        city = inputCity;
        countryCode = inputCountryCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
