package com.example.amir.rehave;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class CommunityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forumLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        String type=getIntent().getExtras().getString("type");

        FloatingActionButton review = (FloatingActionButton) findViewById(R.id.review);
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


        FloatingActionButton add = (FloatingActionButton) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Intent intent=new Intent(CommunityActivity.this,CommunityActivity.class);
//                intent.putExtra("type","admin");
//                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }
}
