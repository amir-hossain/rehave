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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.AddictionInfoDetailsActivity;
import com.example.amir.rehave.MainActivity;
import com.example.amir.rehave.PostActivity;
import com.example.amir.rehave.R;
import com.example.amir.rehave.adapter.AdminListAdapter;
import com.example.amir.rehave.manager.SharedPrefManager;
import com.example.amir.rehave.model.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements AdminListAdapter.ItemClicked {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;
    private View rootView;

    public static AdminFragment newInstance() {
        AdminFragment fragment = new AdminFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.admin_fragment, container, false);


        FloatingActionButton add = (FloatingActionButton) rootView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),PostActivity.class));
            }
        });

        FloatingActionButton community = (FloatingActionButton) rootView.findViewById(R.id.community);
        community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                runFragment(SharedPrefManager.ADMIN_TYPE);

            }
        });


        getData();

        myOnClickListener = new AdminFragment.MyOnClickListener(getActivity());

        recyclerView =rootView.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();

        return rootView;

    }

    private void runFragment(String type) {

        Bundle bundle = new Bundle();
        bundle.putString(SharedPrefManager.TYPE_PREF,type);

        CommunityFragment fragment =CommunityFragment.newInstance();
        fragment.setArguments(bundle);


        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_layout, CommunityFragment.newInstance(),"findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
        SharedPreferences settings = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
        return true;
    }

    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                DataModel value=null;
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    for(DataSnapshot snap : singleSnapshot.getChildren()) {
                        value=snap.getValue(DataModel.class);
                        Log.d("Fire value", "Value is: " + value.getTitle());
                        data.add(new DataModel(value.getId(),value.getTitle(),null,value.getSection()));
                    }

                }
                adapter = new AdminListAdapter(data,getActivity(),myOnClickListener,AdminFragment.this);
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
            this.edit(v);
        }
    }

    private void delete(View v){
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getId();
//            Toast.makeText(this,"delete "+key,Toast.LENGTH_SHORT).show();
        String topic=data.get(index).getSection();
        String path="data/info/"+key;
        if(topic.equals(getResources().getString(R.string.relapse_protection))){
            path="data/pro/"+key;
        }else if(topic.equals(getResources().getString(R.string.archive))){
            path="data/arch/"+key;
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        database.getReference(path).setValue(null);
        data.remove(index);
        adapter = new AdminListAdapter(data,getActivity(),myOnClickListener,AdminFragment.this);
        recyclerView.setAdapter(adapter);
    }

    private void edit(View v){
        int index =recyclerView.getChildLayoutPosition(v);
        String key=data.get(index).getId();
        String topic=data.get(index).getSection();
        String path="data/info/"+key;
        if(topic.equals(getResources().getString(R.string.relapse_protection))){
            path="data/pro/"+key;
        }else if(topic.equals(getResources().getString(R.string.archive))){
            path="data/arch/"+key;
        }
//            Toast.makeText(this,"edit "+key,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(),PostActivity.class);
        intent.putExtra("path",path);
        startActivity(intent);

    }



    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int index =recyclerView.getChildLayoutPosition(v);
            String key=data.get(index).getId();
//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,AddictionInfoDetailsActivity.class);
            intent.putExtra("key",key);
            context.startActivity(intent);
        }


    }




}
