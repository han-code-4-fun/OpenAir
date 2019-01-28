package project.examlple.com.openair.ui;

import androidx.appcompat.app.AppCompatActivity;
import project.examlple.com.openair.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FlashActivity extends AppCompatActivity {

    private static final long splashScreenTime = 3000l;

    private ImageView flash;

    private Button skipButton;

    private Handler myHandler;

    private Runnable myRunnable;

    private boolean isHandlerRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        isHandlerRunning = false;

        //countriesAdapter button that can be used to skip flash, which in the future, this can be countriesAdapter dynamical image such as an ad
        skipButton = findViewById(R.id.skip_button);

        flash = findViewById(R.id.flash_iv);

        //dynamically assign image, potentially can use for internet image instead of local's
        flash.setImageResource(R.drawable.flash);

        //in the future, this can be used for navigate user to browsers if user clicked the flash image.
        flash.setOnClickListener(null);


        //skip this splash screen
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myHandler.removeCallbacks(myRunnable);

                startMainActivity();
            }
        });


        myHandler = new Handler();
        myHandler.postDelayed(myRunnable = new Runnable() {
            @Override
            public void run() {
                skipButton.setOnClickListener(null);
                startMainActivity();
            }
        }, splashScreenTime);

    }





    private void startMainActivity()
    {
        Intent openMain = new Intent(FlashActivity.this, CountryActivity.class);
        startActivity(openMain);
        //to make sure user cannot go back to this page
        finish();
    }
}
