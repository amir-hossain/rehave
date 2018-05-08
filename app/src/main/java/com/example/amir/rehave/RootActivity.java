package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.amir.rehave.AdminActivity;
import com.example.amir.rehave.MainActivity;

public class RootActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        String name = preferences.getString("name", null);

        if(name!=null){
            if (name.equals("Admin")) {
                Intent intent = new Intent(this, AdminActivity.class);
                this.startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
                finish();
            }
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
        }

    }
}
