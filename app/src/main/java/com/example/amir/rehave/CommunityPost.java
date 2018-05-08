package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.others.CommunityPostModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class CommunityPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_post);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.postLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final EditText post=findViewById(R.id.post);

        Button button=findViewById(R.id.post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences= getSharedPreferences("id", Context.MODE_PRIVATE);
                String name=preferences.getString("name",null);
                String userId=preferences.getString("id",null);

                String imput=post.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String path="community/post/";
                DatabaseReference tempRef=database.getReference(path);
                String postId=tempRef.push().getKey();
                path="community/post/"+postId;
                DatabaseReference mainRef=database.getReference(path);
                String[] dateTime=getcurrentDateaAndTime();

                if(name.equals("Admin")){
                    mainRef.setValue(new CommunityPostModel(userId,postId,imput,name,dateTime[0],dateTime[1],true));
                    Toast.makeText(getApplicationContext(),R.string.acceptMessage,Toast.LENGTH_SHORT).show();
                }else{
                    mainRef.setValue(new CommunityPostModel(userId,postId,imput,name,dateTime[0],dateTime[1],false));
                    Toast.makeText(getApplicationContext(),R.string.communityMessage,Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(CommunityPost.this,CommunityActivity.class));
                finish();


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(CommunityPost.this,CommunityActivity.class));
        finish();
        return true;
    }

    public String[] getcurrentDateaAndTime() {
        String []dateTime=new String[2];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String formatedDate =dateFormat.format(Calendar.getInstance().getTime());
        dateTime=formatedDate.split(" ");
        return dateTime;
    }
}
