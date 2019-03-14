package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.amir.rehave.model.DataModel;
import com.example.amir.rehave.model.MainFragmentData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProtectionDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_details);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.relapse_protection);
        actionBar.setDisplayHomeAsUpEnabled(true);
//        Toast.makeText(this,key,Toast.LENGTH_SHORT).show();
        final TextView titleView=findViewById(R.id.title_view);
        final TextView desView=findViewById(R.id.des_view);

        DataModel data=getIntent().getParcelableExtra("data");

        titleView.setText(data.getTitle());
        desView.setText(data.getPost());

    }
}
