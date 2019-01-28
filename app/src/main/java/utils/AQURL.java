package utils;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public final class AQURL {

    //use a seperate class to build and return varies URL

    public static final String LOG_TAG =AQURL.class.getSimpleName();

    //following variables are used by Uri.parse
    public static final String BASE_URL = "https://api.openaq.org/v1/";

    public static final String PATH_COUNTRY = "countries";
    public static final String PATH_CITY = "cities";
    public static final String PATH_LOCATION = "locations";
    public static final String PATH_MEASURE = "measurements";

    public static final String PARAM_COUNTRY="country";
    public static final String PARAM_CITY="city";
    public static final String PARAM_LOCATION="location";

    public static final String PARAM_ORDERBY = "order_by";
    public static final String PARAM_SORT= "sort";
    public static final String PARAM_LIMIT = "limit";

    public static final String VALUE_DATE = "date";
    public static final String VALUE_DESC = "desc";
    public static final String VALUE_ASC = "asc";

    public static final String VALUE_CITY="city";

    public static final String VALUE_LOCATION="location";



    public static URL getListCountriesURL()
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_COUNTRY).build();

        return  URLCreator(input);
    }

    public static URL getListCitiesURL(String countryCode)
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                    .appendPath(PATH_CITY)
                    .appendQueryParameter(PARAM_COUNTRY,countryCode)
                    .appendQueryParameter(PARAM_ORDERBY, VALUE_CITY)
                    .appendQueryParameter(PARAM_SORT,VALUE_ASC)
                    .build();

        return URLCreator(input);
    }

    public static URL getListLocationsURL(String countryCode, String cityName)
    {
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_LOCATION)
                .appendQueryParameter(PARAM_COUNTRY,countryCode)
                .appendQueryParameter(PARAM_CITY, cityName)
                .appendQueryParameter(PARAM_ORDERBY, VALUE_CITY)
                .appendQueryParameter(PARAM_SORT,VALUE_ASC)
                .build();

        return URLCreator(input);
    }

    public static URL getDetailMeasurementURL(
            String countryCode, String cityName,String location)
    {
        //based on requirement, only show 7 results
        Uri input = Uri.parse(BASE_URL).buildUpon()
                .appendPath(PATH_MEASURE)
                .appendQueryParameter(PARAM_COUNTRY,countryCode)
                .appendQueryParameter(PARAM_CITY, cityName)
                .appendQueryParameter(PARAM_LOCATION, location)
                .appendQueryParameter(PARAM_ORDERBY, VALUE_DATE)
                .appendQueryParameter(PARAM_SORT, VALUE_DESC)
                .appendQueryParameter(PARAM_LIMIT, "7")
                .build();

        return URLCreator(input);
    }


    //re-useable function to handle finally step of creating a URL
    public static URL URLCreator(Uri inputUri)
    {
        URL output = null;
        try
        {
            output = new URL(inputUri.toString());

        }catch (MalformedURLException e)
        {
            Log.e(LOG_TAG, "error creating URL");
        }
        return output;
    }
}
