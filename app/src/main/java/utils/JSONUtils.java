package utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


import Data.City;
import Data.Country;
import Data.Measurement;
import Data.Location;

public class JSONUtils {
    private static final String LOG_TAG = JSONUtils.class.getSimpleName();

    //Fetching Json from URL, and create Datamodel's class object from different JSON response


    public static ArrayList<Country> getCounties()
    {
        URL url = AQURL.getListCountriesURL();
        ArrayList<Country> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    Country temp = new Country(
                            getPropertyInString(current,"name"),
                            getPropertyInString(current,"code")
                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
          Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }


    public static ArrayList<City> getCities(String countryCode)
    {
        URL url = AQURL.getListCitiesURL(countryCode);
        ArrayList<City> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    City temp = new City(
                            getPropertyInString(current, "city"),
                            getPropertyInString(current, "country")
                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
            Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }


    public static ArrayList<Location> getLocations(String countryCode, String cityName)
    {
        URL url = AQURL.getListLocationsURL(countryCode,cityName);
        ArrayList<Location> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    Location temp = new Location(
                            getPropertyInString(current, "country"),
                            getPropertyInString(current, "city"),
                            getPropertyInString(current, "location"),
                            getPropertyInString(current, "lastUpdated")
                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
            Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }

    public static ArrayList<Measurement> getMeasurements(String countryCode, String cityName, String location)
    {
        URL url = AQURL.getDetailMeasurementURL(countryCode,cityName,location);
        ArrayList<Measurement> output = new ArrayList<>();
        try {
            Log.i(LOG_TAG, url.toString());
            String stringResult = NetworkUtils.getJSONFromURL(url);
            JSONObject root = new JSONObject(stringResult);
            JSONArray results = getJSONArrayFromJSONObj(root,"results");
            if(results!=null)
            {
                for (int i = 0; i < results.length(); i++) {
                    JSONObject current = results.getJSONObject(i);
                    JSONObject date = getJSONObj(current, "date");
                    JSONObject coor = getJSONObj(current, "coordinates");

                    Measurement temp = new Measurement(
                            getPropertyInString(current, "country"),
                            getPropertyInString(current, "city"),
                            getPropertyInString(current, "location"),
                            getPropertyInString(date, "utc"),
                            getPropertyInString(current,"parameter"),
                            getPropertyInDouble(current, "value"),
                            getPropertyInString(current, "unit"),
                            getPropertyInDouble(coor, "longitude"),
                            getPropertyInDouble(coor, "latitude")

                    );
                    output.add(temp);
                }
            }
        } catch (JSONException j) {
            Log.e(LOG_TAG,  j.getStackTrace().toString());
        } catch (IOException e) {
            Log.e(LOG_TAG,  e.getStackTrace().toString());
        }
        return output;
    }




    //handle JSON property
    private static String getPropertyInString
        (JSONObject inputObj, String inputProperty)throws JSONException
    {
        if(inputObj.has(inputProperty))
        {
            return inputObj.getString(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '"+ inputProperty+"'");
        return "";
    }



    //re-usedable method that handle JSONArray property
    private static JSONArray getJSONArrayFromJSONObj
    (JSONObject inputObj, String arrayName)throws JSONException
    {
        if(inputObj.has(arrayName))
        {
            return inputObj.getJSONArray(arrayName);
        }
        Log.e(LOG_TAG,"no such JSON array: '"+ arrayName+"'");
        return null;
    }


    //re-usedable method that  handle JSON object
    private static JSONObject getJSONObj
            (JSONObject inputObj, String objName)throws JSONException
    {
        if(inputObj.has(objName))
        {
            return inputObj.getJSONObject(objName);
        }
        Log.e(LOG_TAG,"no such property: '"+ objName+"'");
        return null;
    }


    //re-usedable method that handle JSON double property
    private static Double getPropertyInDouble
    (JSONObject inputObj, String inputProperty)throws JSONException
    {
        if(inputObj.has(inputProperty))
        {
            return inputObj.getDouble(inputProperty);
        }
        Log.i(LOG_TAG, "no such property: '"+ inputProperty+"'");
        return  null;
    }

}
