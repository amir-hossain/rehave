package com.example.amir.rehave.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.CommunityPost;
import com.example.amir.rehave.ForumDetails;
import com.example.amir.rehave.R;
import com.example.amir.rehave.ReviewActivity;
import com.example.amir.rehave.adapter.CommunityListAdapter;
import com.example.amir.rehave.model.CommunityPostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ForumFragment extends Fragment implements CommunityListAdapter.ItemClicked{

    private View view;

    private FloatingActionButton review;

    private FloatingActionButton add;

    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private static RecyclerView recyclerView;

    private static ArrayList<CommunityPostModel> data;

    public static ForumFragment newInstance() {

        ForumFragment fragment = new ForumFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view= inflater.inflate(R.layout.fragment_forum, container, false);

        getData();
        recyclerView =view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

        recyclerView=view.findViewById(R.id.my_recycler_view);

        initilizeFab();

        return view;
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

                adapter = new CommunityListAdapter(data,getContext(),ForumFragment.this);
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
        review = view.findViewById(R.id.review);
        add = view.findViewById(R.id.add);
        SharedPreferences preferences=getActivity().getSharedPreferences("id",Context.MODE_PRIVATE);
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
                        Intent intent=new Intent(getContext(),ReviewActivity.class);
                        startActivity(intent);

                    }
                });
            }

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                    Intent intent=new Intent(getContext(),CommunityPost.class);
                    startActivity(intent);
                }
            });

        }

    }

    @Override
    public void onItemClicked(View v) {
        int index =recyclerView.getChildLayoutPosition(v);
        String post=data.get(index).getPost();
        String key=data.get(index).getPostId();
        Intent intent=new Intent(getContext(),ForumDetails.class);
        intent.putExtra("post",post);
        intent.putExtra("key",key);
        startActivity(intent);

//        Toast.makeText(getApplicationContext(),code+"",Toast.LENGTH_SHORT).show();


    }


}
