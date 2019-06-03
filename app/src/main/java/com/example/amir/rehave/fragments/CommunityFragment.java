package com.example.amir.rehave.fragments;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.CommentActivity;
import com.example.amir.rehave.CommunityPostActivity;
import com.example.amir.rehave.ForumDetails;
import com.example.amir.rehave.R;
import com.example.amir.rehave.ReviewActivity;
import com.example.amir.rehave.adapter.CommunityListAdapter;
import com.example.amir.rehave.manager.SharedPrefManager;
import com.example.amir.rehave.model.CommunityPostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommunityFragment extends Fragment implements CommunityListAdapter.ItemClicked {

    FloatingActionButton review;
    FloatingActionButton add;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<CommunityPostModel> data;

    private View view;

    public static CommunityFragment newInstance() {

        CommunityFragment fragment = new CommunityFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view= inflater.inflate(R.layout.fragment_community, container, false);

        getData();
        recyclerView =view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
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
                data.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    value=snap.getValue(CommunityPostModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    if(value.getReviewStatus()){
                        data.add(new CommunityPostModel(value.getUserId(),value.getPostId(),value.getTitle(),value.getPost(),value.getName(),value.getDate(),value.getTime(),value.getReviewStatus(),value.getCommentCount(),value.getImage()));
                    }

                }

                adapter = new CommunityListAdapter(data,getActivity(),CommunityFragment.this);
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
        review = (FloatingActionButton) view.findViewById(R.id.review);
        add = (FloatingActionButton) view.findViewById(R.id.add);
        String name=SharedPrefManager.getInstance(getContext()).getString(SharedPrefManager.NAME_PREF);
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
                        Intent intent=new Intent(getActivity(),ReviewActivity.class);
                        startActivity(intent);
                    }
                });
            }

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent=new Intent(getActivity(), CommunityPostActivity.class);
                    startActivity(intent);

                }
            });

        }

    }


    @Override
    public void onItemClicked(View v) {
        int index =recyclerView.getChildLayoutPosition(v);
        String post=data.get(index).getPost();
        String postId=data.get(index).getPostId();
        Intent intent=new Intent(getActivity(),ForumDetails.class);
        intent.putExtra("post",post);
        intent.putExtra(SharedPrefManager.ID_PREF,postId);
        startActivity(intent);
    }

    private void comment(View v) {
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getPostId();
        String count=data.get(index).getCommentCount();
//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(),CommentActivity.class);
        intent.putExtra("key",key);
        intent.putExtra("count",count);
        startActivity(intent);
    }


}