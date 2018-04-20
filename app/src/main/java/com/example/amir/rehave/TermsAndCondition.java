package com.example.amir.rehave;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class TermsAndCondition extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_condition);
        CheckBox tcTick=findViewById(R.id.tc_tick);
        final Button tcBtn=findViewById(R.id.tc_button);
        tcTick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    tcBtn.setBackgroundColor(getResources().getColor(R.color.darkTeal));
                    tcBtn.setOnClickListener(TermsAndCondition.this);
                }else{
                    tcBtn.setBackgroundColor(Color.WHITE);
                    tcBtn.setOnClickListener(null);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getSharedPreferences("terms", MODE_PRIVATE).edit();
        editor.putBoolean("opened",true);
        editor.commit();
        startActivity(new Intent(TermsAndCondition.this,MainActivity.class));

    }
}
