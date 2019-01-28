package project.examlple.com.openair.ui;

import Data.Location;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import asynctask.LocationAsyncTask;
import project.examlple.com.openair.R;
import recyclerview.LocationAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity
    implements LocationAdapter.CustomListItemClickListener{

    public static final String LOG_TAG = LocationActivity.class.getSimpleName();

    private List<Location> locations ;
    private RecyclerView recyclerView;
    private LocationAdapter locationAdapter;
    private String countryName;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        recyclerViewInitilization();

        Intent intent = getIntent();

        //getting data from last activity and query in the background in the firstplace
        if( intent.hasExtra("countryCode")
                && intent.hasExtra("city")
                && intent.hasExtra("countryName")
        )
        {
            this.countryName =  intent.getStringExtra("countryName");

            LocationAsyncTask loadLocations = new LocationAsyncTask(
                    intent.getStringExtra("countryCode"),
                    intent.getStringExtra("city"),
                    locations,
                    locationAdapter,
                    progressBar);
            loadLocations.execute();
        }else{
            Log.e(LOG_TAG, "error passing Intent data from CountryActivity");
        }

    }



    //this allows program navigates to measurement activity based on different
    //locations user selected
    @Override
    public void customOnListItemClick(int clickedItemIndex) {
        Location current = locations.get(clickedItemIndex);
        String country = current.getCountryCode();
        String city = current.getCity();
        String location = current.getLocation();

        Intent openMeasurement = new Intent(LocationActivity.this, MeasurementActivity.class);
        openMeasurement.putExtra("countryName", countryName);
        openMeasurement.putExtra("country", country);
        openMeasurement.putExtra("city", city);
        openMeasurement.putExtra("location", location);
        startActivity(openMeasurement);
    }

    private void recyclerViewInitilization()
    {
        //display progressbar before data is loaded
        progressBar = findViewById(R.id.progressView_location);
        progressBar.setVisibility(View.VISIBLE);

        locations = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview_location);
        locationAdapter = new LocationAdapter(locations, this);

        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setAdapter(locationAdapter);
    }


}
