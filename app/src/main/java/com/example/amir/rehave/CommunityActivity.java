package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.amir.rehave.adapter.CommunityListAdapter;
import com.example.amir.rehave.model.CommunityPostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityActivity extends AppCompatActivity implements CommunityListAdapter.ItemClicked {

    FloatingActionButton review;
    FloatingActionButton add;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CommunityPostModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forum);
        actionBar.setDisplayHomeAsUpEnabled(true);

        getData();
        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

        recyclerView=findViewById(R.id.my_recycler_view);

        initilizeFab();

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
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    value=snap.getValue(CommunityPostModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    if(value.getReviewStatus()){
                        data.add(new CommunityPostModel(value.getUserId(),value.getPostId(),value.getTitle(),value.getPost(),value.getName(),value.getDate(),value.getTime(),value.getReviewStatus(),value.getCommentCount()));
                    }

                }

                adapter = new CommunityListAdapter(data,getApplicationContext(),CommunityActivity.this);
                recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }

    private void initilizeFab() {
        review = (FloatingActionButton) findViewById(R.id.review);
        add = (FloatingActionButton) findViewById(R.id.add);
        SharedPreferences preferences=getSharedPreferences("id",Context.MODE_PRIVATE);
        String name =preferences.getString("name",null);
        if(name==null){
            review.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        }else {
            if(!name.equals("Admin")){
                review.setVisibility(View.GONE);

            }else {
                review.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                        Intent intent=new Intent(CommunityActivity.this,ReviewActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    Intent intent=new Intent(CommunityActivity.this,CommunityPost.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onItemClicked(View v) {
        int index =recyclerView.getChildLayoutPosition(v);
        String post=data.get(index).getPost();
        String key=data.get(index).getPostId();
        Intent intent=new Intent(getApplicationContext(),ForumDetails.class);
        intent.putExtra("post",post);
        intent.putExtra("key",key);
        startActivity(intent);
        finish();

//        Toast.makeText(getApplicationContext(),code+"",Toast.LENGTH_SHORT).show();


    }

    private void comment(View v) {
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getPostId();
        String count=data.get(index).getCommentCount();
//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),CommentActivity.class);
        intent.putExtra("key",key);
        intent.putExtra("count",count);
        startActivity(intent);
        finish();
    }


}