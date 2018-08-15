package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.example.amir.rehave.others.ArchiveListAdapter;
import com.example.amir.rehave.others.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ArchiveActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.archive);
        actionBar.setDisplayHomeAsUpEnabled(true);

        myOnClickListener = new ArchiveActivity.MyOnClickListener(this);
        getData();



        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();


    }

    DataModel value=null;

    private void getData(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("data/arch");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    value = singleSnapshot.getValue(DataModel.class);
                    data.add(new DataModel(value.getId(),value.getTitle(),value.getPost()));


                }

                adapter = new ArchiveListAdapter(getApplicationContext(),data,myOnClickListener);
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

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int index =recyclerView.getChildLayoutPosition(v);
            String url=data.get(index).getPost();

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }


    }
}
