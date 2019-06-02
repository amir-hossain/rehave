package com.example.amir.rehave;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.rehave.manager.SharedPrefManager;
import com.example.amir.rehave.model.CommunityPostModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ForumPostActivity extends AppCompatActivity {
    private ImageView ivNewPostPostImage;

    private boolean photoSelected = false;
    private Uri selectedImageURI;

    private String userId;

    private ProgressDialog progressDialog;

    StorageReference storageReference;

    private static final int RC_PHOTO_PICKER_POST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_post);

        userId=SharedPrefManager.getInstance(this).getString("id");

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.postLabel);
        actionBar.setDisplayHomeAsUpEnabled(true);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ivNewPostPostImage = findViewById(R.id.post_image);


        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference()
                .child("postImages");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_PHOTO_PICKER_POST){
            if (resultCode == RESULT_OK){
                selectedImageURI = data.getData();
                ivNewPostPostImage.setImageURI(selectedImageURI);
                photoSelected = true;
            }
        }
    }

    private void uploadImage(Uri selectedImageURI, final String message, final String title) {
        StorageReference photoReference = storageReference.child("/image");
        photoReference.putFile(selectedImageURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downlaodURI = taskSnapshot.getDownloadUrl();

                insertWithImage(downlaodURI.toString(), message,title);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("error",exception.getMessage());
            }
        });
    }

    private void insertWithImage(String imageUrl, String message,String title) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path="community/post/";
        DatabaseReference tempRef=database.getReference(path);
        String postId=tempRef.push().getKey();
        path="community/post/"+postId;
        DatabaseReference mainRef=database.getReference(path);
        String[] dateTime=getcurrentDateaAndTime();



            mainRef.setValue(new CommunityPostModel(userId,postId,title,message,"Admin",dateTime[0],dateTime[1],true,imageUrl));
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),R.string.acceptMessage,Toast.LENGTH_SHORT).show();
            Utils.notification++;
            onBackPressed();
    }

    public String[] getcurrentDateaAndTime() {
        String []dateTime;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        String formatedDate =dateFormat.format(Calendar.getInstance().getTime());
        dateTime=formatedDate.split(" ");
        return dateTime;
    }
}
