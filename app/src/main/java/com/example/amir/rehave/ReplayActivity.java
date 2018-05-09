package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.amir.rehave.others.CommentDataModel;
import com.example.amir.rehave.others.CommentListAdapter;
import com.example.amir.rehave.others.RepalayListAdapter;
import com.example.amir.rehave.others.ReplayDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ReplayActivity extends AppCompatActivity{
    ImageButton send;
    EditText comment;

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ReplayDataModel> data;
    private String type;
    String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_replay);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.replayTitle);
        actionBar.setDisplayHomeAsUpEnabled(true);

        postId=getIntent().getExtras().getString("key");

        getData();
        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        recyclerView=findViewById(R.id.my_recycler_view);

        comment=findViewById(R.id.comment);
        send=findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imput=comment.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                String commentId=getIntent().getExtras().getString("key");
                String count=getIntent().getExtras().getString("count");
                int tempCount=Integer.parseInt(count);
                SharedPreferences preferences=getSharedPreferences("id", Context.MODE_PRIVATE);
                String name=preferences.getString("name","null");

                DatabaseReference postref=database.getReference("community/comment/"+commentId);
                tempCount++;
                postref.child("replayCount").setValue(""+tempCount);

                String path="community/replay/";
                DatabaseReference tempRef=database.getReference(path);
                String replayId=tempRef.push().getKey();
                path=path+replayId;
                DatabaseReference mainRef=database.getReference(path);
                String[] dateTime=getcurrentDateaAndTime();
                mainRef.setValue(new ReplayDataModel(name,dateTime[0],dateTime[1],commentId,imput),new
                        DatabaseReference.CompletionListener() {

                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                String postId=getIntent().getExtras().getString("postId",null);
                                Intent intent=new Intent(getApplicationContext(),CommentActivity.class);
                                intent.putExtra("key",postId);
                                startActivity(intent);
                                finish();
                            }
                        });

            }
        });
    }

    public String[] getcurrentDateaAndTime() {
        String []dateTime=new String[2];
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String formatedDate =dateFormat.format(Calendar.getInstance().getTime());
        dateTime=formatedDate.split(" ");
        return dateTime;
    }

    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("community/replay/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                ReplayDataModel value=null;
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    value=snap.getValue(ReplayDataModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    if(postId.equals(value.getCommentId())){
                        data.add(new ReplayDataModel(value.getName(),value.getDate(),value.getTime(),value.getCommentId(),value.getReplay()));

                    }
                }

                adapter = new RepalayListAdapter(data,getApplicationContext());
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
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=new Intent(getApplicationContext(),CommentActivity.class);
        String postId=getIntent().getExtras().getString("postId",null);
        intent.putExtra("key",postId);
        startActivity(intent);
        finish();
        return true;
    }

}


