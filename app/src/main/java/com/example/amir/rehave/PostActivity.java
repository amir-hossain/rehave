package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.amir.rehave.fragments.AdminFragment;
import com.example.amir.rehave.model.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String subject;
    EditText titleView;
    EditText postView;
    Spinner spinner;
    int selectedPosition;
    String path=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        if(getIntent().getExtras()!=null){
            path=getIntent().getExtras().getString("path");
        }

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.postLabel);
        if(path!=null){
            actionBar.setTitle(R.string.editLabel);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);



        creatSpinner();
        titleView=findViewById(R.id.title);
        postView=findViewById(R.id.post);
        Button postBtn=findViewById(R.id.post_button);
        if(path!=null){
            postBtn.setText(R.string.editLabel);
        }
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleView.getText().toString().trim();
                String post=postView.getText().toString().trim();
                postData(title,post,subject);

            }
        });
        if(path!=null){
            getData();
        }


    }

    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//               Log.d("xxxxxxxxxx",dataSnapshot.getValue().toString());
                DataModel value=dataSnapshot.getValue(DataModel.class);
                titleView.setText(value.getTitle());
                postView.setText(value.getPost());
                if(value.getSection().equals(getResources().getString(R.string.relapse_protection))){
                    spinner.setSelection(1);
                }else if(value.getSection().equals(getResources().getString(R.string.archive))){
                    spinner.setSelection(2);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
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
        DataModel model=new DataModel(key,title,post,subject,null);
        mainRef.setValue(model,new
                DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                           startActivity(new Intent(getApplicationContext(),AdminFragment.class));
                    }
                });



    }


    private void creatSpinner() {
        // Spinner element
        spinner =findViewById(R.id.subject);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add(getResources().getString(R.string.adiction_information));
        categories.add(getResources().getString(R.string.relapse_protection));
        categories.add(getResources().getString(R.string.archive));

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
