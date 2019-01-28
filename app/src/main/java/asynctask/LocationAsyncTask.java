package asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import Data.Location;
import recyclerview.LocationAdapter;
import utils.JSONUtils;

public class LocationAsyncTask extends AsyncTask<Void, Void, ArrayList<Location>> {

    private ProgressBar progressBar;

    private String city;
    private String country;
    private List<Location> locationsList;
    private LocationAdapter locationAdapter;

    public LocationAsyncTask(String inputCountry,
                             String inputCity,
                             List<Location> locations,
                             LocationAdapter locationAdapter,
                             ProgressBar progressBar) {
        country = inputCountry;
        city = inputCity;
        locationsList = locations;
        this.locationAdapter = locationAdapter;
        this.progressBar = progressBar;
    }

    @Override
    protected ArrayList<Location> doInBackground(Void... voids) {
        return JSONUtils.getLocations(country, city);
    }

    @Override
    protected void onPostExecute(ArrayList<Location> locations) {
        super.onPostExecute(locations);
        progressBar.setVisibility(View.INVISIBLE);
        locationsList.clear();
        locationsList.addAll(locations);
        locationAdapter.notifyDataSetChanged();
    }
}
