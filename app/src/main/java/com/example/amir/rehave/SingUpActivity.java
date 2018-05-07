package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.others.SignUpModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUpActivity extends AppCompatActivity implements View.OnClickListener{
    EditText userName;
    EditText phonEmail;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.singupLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        userName=findViewById(R.id.phone_email);
        phonEmail=findViewById(R.id.phone_email);
        password=findViewById(R.id.password);
        final Button singUp=findViewById(R.id.button);
        CheckBox checkBox=findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    singUp.setBackgroundColor(getResources().getColor(R.color.darkTeal));
                    singUp.setOnClickListener(SingUpActivity.this);
                }else {
                    singUp.setBackgroundColor(getResources().getColor(R.color.color_white));

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        String name=userName.getText().toString();
        String pass=password.getText().toString();
        String phoneEmail=phonEmail.getText().toString();

        postData(name,pass,phoneEmail);
    }

    private void postData(String name,String pass,String phoneEmail) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path="auth/";
        DatabaseReference mainRef=database.getReference(path);
        SignUpModel model=new SignUpModel(name,pass,phoneEmail);
        mainRef.push().setValue(model,new
                DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        Toast.makeText(getApplicationContext(),"sucessfully registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),loginActivity.class));
                    }
                });



    }
}