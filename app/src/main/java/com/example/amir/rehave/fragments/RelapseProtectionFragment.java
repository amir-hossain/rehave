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

import com.example.amir.rehave.ActivityInfoDetails;
import com.example.amir.rehave.Constants;
import com.example.amir.rehave.ItemClickListener;
import com.example.amir.rehave.R;
import com.example.amir.rehave.link.LinkListeners;
import com.example.amir.rehave.link.LinkMethods;
import com.example.amir.rehave.model.DataModel;
import com.example.amir.rehave.adapter.ListAdpter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RelapseProtectionFragment extends Fragment implements ItemClickListener, LinkListeners.DataTableListener{

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView;
    private ArrayList<DataModel> data;

    private View view;

    public static RelapseProtectionFragment newInstance() {
        RelapseProtectionFragment fragment = new RelapseProtectionFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_relapse_protection, container, false);

        getData();

        recyclerView =view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    private void getData(){

        LinkMethods.getInstance().setDataTableListener(getContext(), Constants.Section.PROTECTION.toInt(),this);
    }

    @Override
    public void itemClick(DataModel data) {

        Log.d("xzzzzzzzzzz", "xxxxxxx");

//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getContext(), ActivityInfoDetails.class);
        intent.putExtra("data",data);
        getContext().startActivity(intent);
    }

    @Override
    public void listenDatable(List<DataModel> datas) {
        adapter = new ListAdpter(data,RelapseProtectionFragment.this);
        recyclerView.setAdapter(adapter);
    }
}
