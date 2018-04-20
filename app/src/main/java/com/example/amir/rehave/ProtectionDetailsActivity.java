package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProtectionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.menuLabel2);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
