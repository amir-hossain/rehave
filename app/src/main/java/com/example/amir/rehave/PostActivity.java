package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amir.rehave.others.DataModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String subject;
    EditText titleView;
    EditText postView;
    int selectedPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.postLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        creatSpinner();
        titleView=findViewById(R.id.title);
        postView=findViewById(R.id.post);
        Button postBtn=findViewById(R.id.post_button);
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleView.getText().toString().trim();
                String post=postView.getText().toString().trim();
                postData(title,post,subject);






            }
        });

    }

    private void postData(String title,String post,String subject) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path="data/info/";
        if(selectedPosition==1){
            path="data/pro/";
        }else if(selectedPosition==2){
            path="data/arch/";
        }

        DatabaseReference tempRef = database.getReference(path);

        String key=tempRef.push().getKey();
        DatabaseReference mainRef=database.getReference(path+key);
        DataModel model=new DataModel(key,title,post,subject);
        mainRef.setValue(model,new
                DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                           startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    }
                });



    }


    private void creatSpinner() {
        // Spinner element
        Spinner spinner =findViewById(R.id.subject);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add(getResources().getString(R.string.menuLabel1));
        categories.add(getResources().getString(R.string.menuLabel2));
        categories.add(getResources().getString(R.string.menuLabel3));

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPosition=position;
        // On selecting a spinner item
        subject = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + subject, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
