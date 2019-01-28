package asynctask;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import Data.Measurement;
import recyclerview.MeasurementAdapter;
import utils.JSONUtils;

public class MeasurementAsyncTask extends AsyncTask<Void, Void, ArrayList<Measurement>> {


    private ProgressBar progressBar;
    private List<Measurement> listDetails;
    private MeasurementAdapter measurementAdapter;
    private String city;
    private String country;
    private String location;

    public MeasurementAsyncTask(String country, String city, String location,
                                List<Measurement> inputListDetails,
                                MeasurementAdapter inputMeasurementAdapter,
                                ProgressBar progressBar) {
        this.city = city;
        this.country = country;
        this.location = location;
        this.listDetails = inputListDetails;
        this.measurementAdapter = inputMeasurementAdapter;
        this.progressBar = progressBar;
    }

    @Override
    protected ArrayList<Measurement> doInBackground(Void... voids) {
        return JSONUtils.getMeasurements(country, city,location);
    }

    @Override
    protected void onPostExecute(ArrayList<Measurement> detailMeasurements) {
        super.onPostExecute(detailMeasurements);
        progressBar.setVisibility(View.INVISIBLE);
        listDetails.clear();
        listDetails.addAll(detailMeasurements);
        measurementAdapter.notifyDataSetChanged();
    }
}
