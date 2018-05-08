package com.example.amir.rehave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalash_screen);
        SharedPreferences prefs = getSharedPreferences("terms", MODE_PRIVATE);
        final boolean opened = prefs.getBoolean("opened", false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!opened) {
                    startActivity(new Intent(SplashScreenActivity.this, TermsAndCondition.class));
                }else {
                    startActivity(new Intent(SplashScreenActivity.this, RootActivity.class));
                }


                finish();

            }

        }, 3000);
    }

}
