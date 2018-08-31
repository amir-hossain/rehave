package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.fragments.CommunityFragment;
import com.google.firebase.database.FirebaseDatabase;

public class EditCommunityPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_community_post);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.editLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);


        Button edit=findViewById(R.id.edit_button);
        final EditText editText=findViewById(R.id.post);
        String text=getIntent().getExtras().getString("post");
        editText.setText(text);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=getIntent().getExtras().getString("key");
               String post=editText.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                database.getReference("community/post/"+id).child("post").setValue(post);
                Toast.makeText(getApplicationContext(),R.string.deleteMessage,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditCommunityPost.this,CommunityFragment.class));
                finish();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(EditCommunityPost.this,CommunityFragment.class));
        finish();
        return true;
    }
}
