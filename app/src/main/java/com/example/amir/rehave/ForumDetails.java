package com.example.amir.rehave;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.amir.rehave.manager.SharedPrefManager;
import com.example.amir.rehave.model.CommunityPostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ForumDetails extends AppCompatActivity {
    String post;

    TextView titleView,postView;
    TextView nameView;
    TextView dateView;
    TextView timeView;
    TextView commentView;
    ImageView img;
    String postId;

    private static ArrayList<CommunityPostModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_details);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.forumDetailsLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);

        postId=getIntent().getExtras().getString(SharedPrefManager.ID_PREF);
        initializeView();

        getData();

//        replayBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
//                CommentListAdapter.clickListener.onItemClicked(itemView, 1);
//            }
//        });


    }

    private void initializeView() {
        this.nameView = findViewById(R.id.user_name);
        this.dateView = findViewById(R.id.date);
        this.timeView = findViewById(R.id.time);
        this.commentView = findViewById(R.id.count);
        this.titleView = findViewById(R.id.title);
        this.postView = findViewById(R.id.post);
        this.img = findViewById(R.id.post_image);

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;

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
                    if(value.getPostId().equals(postId)){
                        CommunityPostModel postModel=new CommunityPostModel(value.getUserId(),value.getPostId(),value.getTitle(),value.getPost(),value.getName(),value.getDate(),value.getTime(),value.getReviewStatus(),value.getCommentCount(),value.getImage());

                        setData(postModel);
                    }



                }

//                    Log.d("Fire value", "Value is: " + data.get(0).getTitle());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }

    private void setData(CommunityPostModel postModel) {
        nameView.setText(postModel.getName());

        if(postModel.getTitle().equals("")){
            titleView.setText(getString(R.string.untitled));

        }else{
            titleView.setText(postModel.getTitle());
        }


        timeView.setText(postModel.getTime());

        dateView.setText(postModel.getDate());

        postView.setText(postModel.getPost());

        if(postModel.getImage()!=null){
            img.setVisibility(View.VISIBLE);
            Glide.with(ForumDetails.this).load(postModel.getImage()).into(img);
        }

        String count=postModel.getCommentCount();
//        if (count.equals("0")){
//            countView.setVisibility(View.GONE);
//        }else {
//            countView.setText(count);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete:
                delete();
                onBackPressed();
                break;
            case R.id.edit:
                String key=getIntent().getExtras().getString("key");
                Intent intent=new Intent(ForumDetails.this,EditCommunityPost.class);
                intent.putExtra("key",key);
                intent.putExtra("post",post);
                startActivity(intent);
                finish();
                break;
                default:
                    onBackPressed();
        }
        return true;
    }





    private void delete(){

        String key=getIntent().getExtras().getString("key");
//            Toast.makeText(this,"delete "+key,Toast.LENGTH_SHORT).show();

        String path="community/post/"+key;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference(path).setValue(null);
        Toast.makeText(getApplicationContext(),R.string.deleteMessage,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
