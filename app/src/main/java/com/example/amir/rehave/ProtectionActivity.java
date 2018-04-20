package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProtectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.menuLabe2);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
