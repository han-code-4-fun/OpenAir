package project.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;


import asynctask.CityAsyncTask;
import asynctask.CountryAsyncTask;
import project.examlple.com.openair.R;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CountryActivity extends AppCompatActivity {

    private Handler handlerBackPress;

    private int numOfTimesBackPressed;

    //spinner contains countries list and cities' list
    private Spinner countriesSpinner, citiesSpinner;
    //button that go to the next activity
    private Button submit;

    //background list stores countries' codes for program to query
    private ArrayList<String> countriesCode;

    private ArrayAdapter<String> countriesAdapter;
    private ArrayAdapter<String> citiesAdapter;

    private TextView emptyView;
    private ProgressBar progressBar;

    private ConnectivityManager cm;
    private NetworkInfo myNetInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);


        layoutInitilization();

        numOfTimesBackPressed = 0;



        if(myNetInfo != null && myNetInfo.isConnected())
        {
            setSpinnerItemSelectListener(countriesSpinner);

            CountryAsyncTask initialLoadCountries = new CountryAsyncTask(
                    countriesAdapter, citiesAdapter, countriesCode, progressBar);
            initialLoadCountries.execute();

            setOnClickListener(submit);
        }else
        {

            //handle completely no internet situation
            progressBar.setVisibility(View.INVISIBLE);
            emptyView.setText("No internet connection");
        }




    }


    @Override
    public void onBackPressed() {

        numOfTimesBackPressed += 1;

        if(numOfTimesBackPressed <= 1)
        {
            Toast.makeText(
                    this,
                    "Tap one more time to exit app.",
                    Toast.LENGTH_SHORT).show();

            handlerBackPress = new Handler();
            final Runnable countDownTwoSecond = new Runnable() {
                @Override
                public void run() {
                    numOfTimesBackPressed -= 1;
                }
            };
            handlerBackPress.postDelayed(countDownTwoSecond, 2000);
        }else{
            super.onBackPressed();
        }

    }

    private void layoutInitilization()
    {
        //display progressbar before data is loaded
        progressBar = findViewById(R.id.progressView);
        progressBar.setVisibility(View.VISIBLE);


        cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //get current network info
        myNetInfo = cm.getActiveNetworkInfo();

        emptyView = findViewById(R.id.emptyView);

        countriesCode = new ArrayList<>();

        countriesSpinner = findViewById(R.id.spinner_country);


        citiesSpinner = findViewById(R.id.spinner_city);

        countriesAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        countriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countriesSpinner.setAdapter(countriesAdapter);

        citiesAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, new ArrayList<String>());
        citiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citiesSpinner.setAdapter(citiesAdapter);

        submit = findViewById(R.id.btn_submit);
    }


    private void setSpinnerItemSelectListener(Spinner countriesSpinner)
    {
        //whenever spinner selection gets changed, will call Async task to update information
        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = countriesCode.get(position);
                CityAsyncTask updateCities = new CityAsyncTask(selected, citiesAdapter, progressBar);
                updateCities.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    private void setOnClickListener(Button submit)
    {
        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(citiesSpinner.getSelectedItem().toString().equals("unused"))
                {
                    Toast.makeText(
                            CountryActivity.this,
                            "Please choose cities that has current data",
                            Toast.LENGTH_LONG).show();
                }else{
                    Intent locationActivity = new Intent(CountryActivity.this, LocationActivity.class);
                    //pass data to next activity
                    locationActivity.putExtra(
                            "countryName",
                            countriesSpinner.getSelectedItem().toString());
                    locationActivity.putExtra(
                            "countryCode",
                            countriesCode.get(countriesSpinner.getSelectedItemPosition()));
                    locationActivity.putExtra("city", citiesSpinner.getSelectedItem().toString());
                    startActivity(locationActivity);
                }
            }
        });
    }





}
