package asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import Data.Country;
import utils.JSONUtils;

public class CountryAsyncTask extends AsyncTask<Void, Void, ArrayList<Country>> {
    ArrayAdapter<String> countriesAdapter;
    ArrayAdapter<String> citiesAdapter;
    ArrayList<String> countriesCode;

    private ProgressBar progressBar;


    public CountryAsyncTask(
            ArrayAdapter<String> countriesAdapter,
            ArrayAdapter<String> citiesAdapter,
            ArrayList<String> countriesCode,
            ProgressBar progressBar)
    {
        this.countriesAdapter = countriesAdapter;
        this.citiesAdapter = citiesAdapter;
        this.countriesCode = countriesCode;
        this.progressBar =progressBar;
    }

    @Override
    protected ArrayList<Country> doInBackground(Void... voids) {

        return JSONUtils.getCounties();
    }

    @Override
    protected void onPostExecute(ArrayList<Country> countries) {
        super.onPostExecute(countries);

        if(countries.size()>0)
        {
            updateCountriesAdapterAndDefaultCity(countries);
        }
    }

    private void updateCountriesAdapterAndDefaultCity(ArrayList<Country> inputList)
    {
        progressBar.setVisibility(View.INVISIBLE);

        countriesAdapter.clear();
        for (Country c :
                inputList) {
            countriesAdapter.add(c.getName());
            countriesCode.add(c.getCode());
        }
        countriesAdapter.notifyDataSetChanged();


        progressBar.setVisibility(View.VISIBLE);
        CityAsyncTask loadInitialCities = new CityAsyncTask(countriesCode.get(0), citiesAdapter,progressBar);
        loadInitialCities.execute();
    }
}
