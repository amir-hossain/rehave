package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amir.rehave.others.AdminListAdapter;
import com.example.amir.rehave.others.CommunityPostModel;
import com.example.amir.rehave.others.DataModel;
import com.example.amir.rehave.others.ReviewListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity implements ReviewListAdapter.ItemClicked{
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CommunityPostModel> data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.reviewTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);


        getData();

        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(ReviewActivity.this,CommunityActivity.class));
        finish();
        return true;
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
                    if(!value.getReviewStatus()){
                        data.add(new CommunityPostModel(value.getUserId(),value.getPostId(),value.getPost(),value.getName(),value.getDate(),value.getTime(),value.getReviewStatus()));
                    }

                }
                    adapter = new ReviewListAdapter(data,getApplicationContext(),ReviewActivity.this);
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

    @Override
    public void onItemClicked(View v, int code) {
        if(code==0){
            this.delete(v);

        }else {
            this.accept(v);
        }
    }

    private void delete(View v){
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getPostId();
//            Toast.makeText(this,"delete "+key,Toast.LENGTH_SHORT).show();

        String path="community/post/"+key;
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(path).setValue(null);
        data.remove(index);

            adapter = new ReviewListAdapter(data,getApplicationContext(),ReviewActivity.this);
            recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            Toast.makeText(getApplicationContext(),R.string.deleteMessage,Toast.LENGTH_SHORT).show();
    }


    private void accept(View v){
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getPostId();
        String path="community/post/"+key;
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(path).child("reviewStatus").setValue(true);
        data.remove(index);
            adapter = new ReviewListAdapter(data,getApplicationContext(),ReviewActivity.this);
            recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            Toast.makeText(getApplicationContext(),R.string.acceptMessage,Toast.LENGTH_SHORT).show();
    }


}
