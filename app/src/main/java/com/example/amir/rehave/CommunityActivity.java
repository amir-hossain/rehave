package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class CommunityActivity extends AppCompatActivity {
    FloatingActionButton review;
    FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        SharedPreferences prefs =getPreferences(Context.MODE_PRIVATE);
        String id = prefs.getString(getString(R.string.id_preference), null);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forumLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String type=getIntent().getExtras().getString("type");
        review = (FloatingActionButton) findViewById(R.id.review);
        add = (FloatingActionButton) findViewById(R.id.add);
        SharedPreferences mSharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        if(mSharedPreferences.contains("id")){
            initilizeFab(type);
        }else {
            review.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        }
    }

    private void initilizeFab(String type) {
        if(type.equals("user")){
            review.setVisibility(View.GONE);
        }
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent=new Intent(CommunityActivity.this,CommunityActivity.class);
//                intent.putExtra("type","admin");
//                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(CommunityActivity.this,CommunityPost.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
