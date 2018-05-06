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

public class loginActivity extends AppCompatActivity{
    String name;
    String pass;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);

        Button loginButton=findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=userName.getText().toString().trim();
                pass=password.getText().toString().trim();
//                Log.d("xxx",pass);
                if(name.equals("1") && pass.equals("1")){
                    startActivity(new Intent(loginActivity.this,AdminActivity.class));

                }else{
                    Toast.makeText(loginActivity.this,R.string.loginErrorMessage,Toast.LENGTH_LONG).show();
                }

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


}
