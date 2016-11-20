package com.ashishyogesh.android.ppfinterestcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Yogesh on 11/19/2016.
 */

public class MainSplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splash_screen);

        // Call splash screen
        callSplashScreen();
    }

    private void callSplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callMainActivity();
            }
        }, 1 * 1000);
    }

    private void callMainActivity() {
        Intent mainActivity = new Intent(MainSplashScreen.this, MainActivity.class);
        startActivity(mainActivity);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
