package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.amir.rehave.others.AdminListAdapter;
import com.example.amir.rehave.others.DataModel;
import com.example.amir.rehave.others.ListAdpter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.adminPageLabel);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                startActivity(new Intent(AdminActivity.this,PostActivity.class));
            }
        });


        getData();

        myOnClickListener = new AdminActivity.MyOnClickListener(this);

        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();


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
                adapter = new AdminListAdapter(data,getApplicationContext(),myOnClickListener);
                recyclerView.setAdapter(adapter);
//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }           private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int index =recyclerView.getChildLayoutPosition(v);
            String key=data.get(index).getId();
//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,InfoDetailsActivity.class);
            intent.putExtra("key",key);
            context.startActivity(intent);
        }


    }




}
