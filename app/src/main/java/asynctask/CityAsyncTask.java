package asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import Data.City;
import utils.JSONUtils;

public class CityAsyncTask extends AsyncTask<Void, Void, ArrayList<City>> {
    private ProgressBar progressBar;

    private String countryCode;
    private ArrayAdapter<String> citiesAdapter;

    public CityAsyncTask(String inputCountryCode,
                         ArrayAdapter<String> citiesAdapter,
                         ProgressBar progressBar){
        countryCode = inputCountryCode;
        this.citiesAdapter = citiesAdapter;
        this.progressBar =progressBar;
    }

    @Override
    protected ArrayList<City> doInBackground(Void... input) {
        return JSONUtils.getCities(countryCode);
    }

    @Override
    protected void onPostExecute(ArrayList<City> cities) {
        super.onPostExecute(cities);
        if(cities.size()>0)
        {
            refreshCitiesList(cities);
        }
    }

    private void refreshCitiesList(ArrayList<City> cities)
    {
        progressBar.setVisibility(View.INVISIBLE);
        citiesAdapter.clear();
        for (City c :
                cities) {
            citiesAdapter.add(c.getCity());
        }
        citiesAdapter.notifyDataSetChanged();
    }
}
