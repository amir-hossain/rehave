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
import android.widget.Toast;

import com.example.amir.rehave.others.AdminListAdapter;
import com.example.amir.rehave.others.CommunityListAdapter;
import com.example.amir.rehave.others.CommunityPostModel;
import com.example.amir.rehave.others.DataModel;
import com.example.amir.rehave.others.ReviewListAdapter;
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
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        SharedPreferences prefs =getPreferences(Context.MODE_PRIVATE);
        String id = prefs.getString(getString(R.string.id_preference), null);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forumLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        getData();
        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        review = (FloatingActionButton) findViewById(R.id.review);
        add = (FloatingActionButton) findViewById(R.id.add);
        recyclerView=findViewById(R.id.my_recycler_view);
        SharedPreferences mSharedPreferences = getSharedPreferences("id", Context.MODE_PRIVATE);
        if(mSharedPreferences.contains("id")){
            if(getIntent().getExtras()!=null){
                type=getIntent().getExtras().getString("type");
                initilizeFab(type);
            }


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
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    value=snap.getValue(CommunityPostModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    if(value.getReviewStatus()){
                        data.add(new CommunityPostModel(value.getUserId(),value.getPostId(),value.getPost(),value.getName(),value.getDate(),value.getTime(),value.getReviewStatus(),value.getCommentCount()));
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

    private void initilizeFab(final String type) {
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
                finish();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent=new Intent(CommunityActivity.this,CommunityPost.class);
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return true;
    }

    @Override
    public void onItemClicked(View v, int code) {
        if(code==0){
//            this.delete(v);

        }else if(code==1){
//            this.delete(v);

        }else {
            this.comment(v);
        }
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
