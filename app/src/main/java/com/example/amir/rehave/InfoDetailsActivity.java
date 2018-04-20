package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class InfoDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.menuLabel1);
        actionBar.setDisplayHomeAsUpEnabled(true);
        int index=getIntent().getExtras().getInt("index");
        Toast.makeText(this,""+index,Toast.LENGTH_SHORT).show();
    }
}
