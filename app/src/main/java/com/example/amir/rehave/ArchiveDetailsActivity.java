package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArchiveDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.menuLabel3);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
