package com.example.amir.rehave.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;
import com.example.amir.rehave.adapter.ArchiveListAdapter;
import com.example.amir.rehave.model.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArchiveFragment extends Fragment {
    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private static RecyclerView recyclerView;

    private static ArrayList<DataModel> data;

    private static View.OnClickListener myOnClickListener;

    private RecyclerView view;

    public static ArchiveFragment newInstance() {
        ArchiveFragment fragment = new ArchiveFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RecyclerView) inflater.inflate(R.layout.fragment_archive, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        view.setLayoutManager(linearLayoutManager);

        getData();

        data = new ArrayList<>();

        return view;
    }



    private void getData(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data/arch");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    DataModel value = singleSnapshot.getValue(DataModel.class);
                    data.add(new DataModel(value.getId(),value.getTitle(),value.getPost()));


                }

                ArchiveListAdapter archiveListAdapter = new ArchiveListAdapter(ArchiveFragment.this,data);
                view.setAdapter(archiveListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }
}
