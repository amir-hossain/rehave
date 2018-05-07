package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.others.SignUpModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class loginActivity extends AppCompatActivity{
    String phoneEmail;
    String pass;
    EditText phoneEmailField;
    EditText password;
    ArrayList<SignUpModel> singUpDatas =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phoneEmailField =findViewById(R.id.phone_email);
        password=findViewById(R.id.password);

        getData();

        Button loginButton=findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEmail= phoneEmailField.getText().toString().trim();
                pass=password.getText().toString().trim();
//                Log.d("xxx",pass);
                check();

            }
        });

        Button singupButton=findViewById(R.id.singup_button);
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(loginActivity.this,SingUpActivity.class));
            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.loginPageLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void check() {
        if(singUpDatas!=null){
            boolean result=false;
            for(int i=0;i<singUpDatas.size();i++){
                String dataBasephoneEmail=singUpDatas.get(i).getPhoneEmail();
                String dataBasePass=singUpDatas.get(i).getPassword();
                if(phoneEmail.equals(dataBasephoneEmail) && pass.equals(dataBasePass)){
                    startActivity(new Intent(getApplicationContext(),AdminActivity.class));
                    result= true;
                    break;
                }else{
                    result= false;
                }
            }

            if(!result){
                Toast.makeText(getApplicationContext(),R.string.loginErrorMessage,Toast.LENGTH_SHORT).show();
            }

        }

    }




    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("auth");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                boolean result=false;
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    SignUpModel value=snap.getValue(SignUpModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    singUpDatas.add(new SignUpModel(value.getName(),value.getPassword(),value.getPhoneEmail()));
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }


}
