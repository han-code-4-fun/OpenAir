package project.examlple.com.openair.ui;

import Data.Measurement;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import asynctask.MeasurementAsyncTask;
import project.examlple.com.openair.R;
import recyclerview.MeasurementAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MeasurementActivity extends AppCompatActivity {

    public static final String LOG_TAG = MeasurementActivity.class.getSimpleName();

    private String countryName;

    private List<Measurement> listDetails;

    private ProgressBar progressBar;

    private TextView location, cityNcountry;
    private Button refresh;
    private RecyclerView recyclerView;


    MeasurementAdapter measurementAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);

        layoutInitialization();

        recyclerViewInitialization();

        //when loading, to get data that is passed from the last activity
        //and query in the background
        final Intent intent  = getIntent();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loadData(intent);
            }
        });

        loadData(intent);
    }

    private void layoutInitialization()
    {


        progressBar = findViewById(R.id.progressView_measurement);
        progressBar.setVisibility(View.VISIBLE);

        location = findViewById(R.id.tv_detail_head_Location);

        cityNcountry = findViewById(R.id.tv_detail_head_city_country);

        refresh = findViewById(R.id.btn_refresh);

        recyclerView = findViewById(R.id.rv_detail_measurement);
    }

    private void recyclerViewInitialization()
    {
        RecyclerView.LayoutManager rvLayoutManager = new LinearLayoutManager(this);

        listDetails = new ArrayList<>();
        measurementAdapter = new MeasurementAdapter(listDetails);

        recyclerView.setLayoutManager(rvLayoutManager);
        recyclerView.setAdapter(measurementAdapter);
    }


    private void loadData(Intent intent)
    { if(intent.hasExtra("country")
            && intent.hasExtra("city")
            && intent.hasExtra("location")
            && intent.hasExtra("countryName"))
    {
        countryName = intent.getStringExtra("countryName");

        location.setText( intent.getStringExtra("location"));
        cityNcountry.setText(intent.getStringExtra("city") + ", "+countryName);
        MeasurementAsyncTask getDetailData = new MeasurementAsyncTask(
                intent.getStringExtra("country"),
                intent.getStringExtra("city"),
                intent.getStringExtra("location"),
                listDetails,
                measurementAdapter,
                progressBar
        );
        getDetailData.execute();

    }else{
        Log.e(LOG_TAG, "error passing Intent data from locationActivity");
    }

    }
}
