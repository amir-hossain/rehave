package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SingUpActivity extends AppCompatActivity implements View.OnClickListener{
    EditText userName=findViewById(R.id.userName);
    EditText phonEmail=findViewById(R.id.phone_email);
    EditText password=findViewById(R.id.password);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);


        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.singupLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final Button singUp=findViewById(R.id.button);
        CheckBox checkBox=findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    singUp.setBackgroundColor(getResources().getColor(R.color.darkTeal));
                }else {
                    singUp.setBackgroundColor(getResources().getColor(R.color.color_white));
                    singUp.setOnClickListener(SingUpActivity.this);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        String name=userName.getText().toString();
        String pass=password.getText().toString();
        String phoneEmail=phonEmail.getText().toString();
    }
}