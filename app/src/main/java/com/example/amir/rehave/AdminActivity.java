package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amir.rehave.others.DataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.adminPageLabel);

//        write demo data


//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference tempRef = database.getReference("info");
//
//        String key=tempRef.push().getKey();
//        DatabaseReference mainRef=database.getReference("info/"+key);
//        DataModel model=new DataModel(key,getResources().getString(R.string.title1),getResources().getString(R.string.des1));
//        mainRef.setValue(model);
//
//        key=tempRef.push().getKey();
//        mainRef=database.getReference("info/"+key);
//        DataModel model2=new DataModel(key,getResources().getString(R.string.title2),getResources().getString(R.string.des2));
//        mainRef.setValue(model2);
    }
}
