package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.amir.rehave.others.CommentDataModel;
import com.example.amir.rehave.others.CommunityPostModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommentActivity extends AppCompatActivity {
    ImageButton send;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.commentTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);

        comment=findViewById(R.id.comment);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imput=comment.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String path="community/comment/";
                DatabaseReference tempRef=database.getReference(path);
                String commentId=tempRef.push().getKey();
                path=path+commentId;
                DatabaseReference mainRef=database.getReference(path);
                String postId=getIntent().getExtras().getString("key");
                String count=getIntent().getExtras().getString("count");
                int tempCount=Integer.parseInt(count);
                SharedPreferences preferences=getSharedPreferences("id", Context.MODE_PRIVATE);
                String name=preferences.getString("name","null");
                DatabaseReference postref=database.getReference("community/post/"+postId);
                tempCount++;
                postref.child("commentCount").setValue(""+tempCount);
                mainRef.setValue(new CommentDataModel(imput,postId,name),new
                        DatabaseReference.CompletionListener() {

                            @Override
                            public void onComplete(DatabaseError databaseError,
                                                   DatabaseReference databaseReference) {
                                finish();
                                startActivity(new Intent(getApplicationContext(),CommunityActivity.class));
                            }
                        });

            }
        });
    }
}
