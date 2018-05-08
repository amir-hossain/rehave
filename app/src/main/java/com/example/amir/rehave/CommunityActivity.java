package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.amir.rehave.others.AdminListAdapter;
import com.example.amir.rehave.others.CommunityPostModel;
import com.example.amir.rehave.others.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity {
    FloatingActionButton review;
    FloatingActionButton add;
    ArrayList<CommunityPostModel> data=new ArrayList<>();

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


    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("community/post/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                CommunityPostModel value=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot snap : singleSnapshot.getChildren()) {
                        value=snap.getValue(CommunityPostModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                        if(!value.getReviewStatus()){
                            data.add(new CommunityPostModel(value.getUserId(),value.getPostId(),null,value.getName(),value.getDate(),value.getTime()));
                        }

                    }

                }
//                adapter = new AdminListAdapter(data,getApplicationContext(),myOnClickListener,AdminActivity.this);
//                recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

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
                Intent intent=new Intent(CommunityActivity.this,ReviewActivity.class);
                startActivity(intent);
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
