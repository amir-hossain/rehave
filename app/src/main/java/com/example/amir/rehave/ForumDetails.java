package com.example.amir.rehave;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.rehave.others.ReviewListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class ForumDetails extends AppCompatActivity {
    String post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forumDetailsLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView postView =findViewById(R.id.post);
        post=getIntent().getExtras().getString("post");
        postView.setText(post);
    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete:
                delete();
                onBackPressed();
                break;
            case R.id.edit:
                String key=getIntent().getExtras().getString("key");
                Intent intent=new Intent(ForumDetails.this,EditCommunityPost.class);
                intent.putExtra("key",key);
                intent.putExtra("post",post);
                startActivity(intent);
                finish();
                break;
                default:
                    onBackPressed();
        }
        return true;
    }





    private void delete(){

        String key=getIntent().getExtras().getString("key");
//            Toast.makeText(this,"delete "+key,Toast.LENGTH_SHORT).show();

        String path="community/post/"+key;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference(path).setValue(null);
        Toast.makeText(getApplicationContext(),R.string.deleteMessage,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
