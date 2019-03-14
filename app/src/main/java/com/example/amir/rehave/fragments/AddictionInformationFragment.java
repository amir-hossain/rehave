package com.example.amir.rehave.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.AddictionInfoDetailsActivity;
import com.example.amir.rehave.R;
import com.example.amir.rehave.model.DataModel;
import com.example.amir.rehave.adapter.ListAdpter;
import com.example.amir.rehave.model.MainFragmentData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddictionInformationFragment extends Fragment {

    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView;
//    private ArrayList<DataModel> data;
private ArrayList<DataModel> data;
    public  View.OnClickListener myOnClickListener;

    private View view;

    public static AddictionInformationFragment newInstance() {

        AddictionInformationFragment fragment = new AddictionInformationFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       view= inflater.inflate(R.layout.fragment_addiction_information, container, false);
        getData();

        myOnClickListener = new MyOnClickListener(getContext());

        recyclerView =view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

        return view;
    } private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data/info");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                DataModel value=null;
                DataModel value=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    value = singleSnapshot.getValue(DataModel.class);
                    data.add(new DataModel(value.getId(),value.getTitle(),value.getPost()));
                }

                adapter = new ListAdpter(data,myOnClickListener);
                recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + value.getTitle());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }

    private  class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;

        }

        @Override
        public void onClick(View v) {
            Log.d("cccccccc", "ccccccccccc");
            int index =recyclerView.getChildLayoutPosition(v);
//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(context,AddictionInfoDetailsActivity.class);
            intent.putExtra("data",data.get(index));
            context.startActivity(intent);
        }
    }


}
