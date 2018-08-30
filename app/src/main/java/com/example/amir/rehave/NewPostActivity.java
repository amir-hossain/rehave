package com.example.amir.rehave;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.rehave.manager.SharedPrefManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class NewPostActivity extends AppCompatActivity {

    private ImageView ivNewPostUserImage;
    private TextView tvNewPostUserName;
    private EditText etNewPostMessage;
    private Button btNewPostAddImage;
    private ImageView ivNewPostPostImage;

    private boolean photoSelected = false;
    private Uri selectedImageURI;

    private ProgressDialog progressDialog;

    StorageReference storageReference;

    private static final int RC_PHOTO_PICKER_POST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        setTitle("New Post");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ivNewPostUserImage = findViewById(R.id.iv_new_post_user_image);
        tvNewPostUserName = findViewById(R.id.tv_new_post_user_name);
        etNewPostMessage = findViewById(R.id.et_new_post_message);
        btNewPostAddImage = findViewById(R.id.bt_new_post_add_a_photo);
        ivNewPostPostImage = findViewById(R.id.iv_new_post_post_image);

        progressDialog = new ProgressDialog(this);

        storageReference = FirebaseStorage.getInstance().getReference()
                .child("postImages");

        btNewPostAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER_POST);
            }
        });

//        String imgurl = SharedPrefManager.getInstance(com.example.shivamvk.facebookandroid.NewPostActivity.this).getUserImage();
//        imgurl = imgurl.replace("/profilepicture/","%2Fprofilepicture%2F");

//        Picasso.get()
//                .load(imgurl)
//                .placeholder(R.drawable.placeholderboy)
//                .into(ivNewPostUserImage);

        tvNewPostUserName.setText(SharedPrefManager.getInstance(this).getString("name"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_post){
            String message = etNewPostMessage.getText().toString();
            String imageurl = "";
            if (message.isEmpty()){
                Toast.makeText(this, "Please write a post message", Toast.LENGTH_SHORT).show();
            } else {
                message = message.replace(" ", "+");
                progressDialog.setMessage("Posting on yout timeline...");
                progressDialog.show();
                if (photoSelected){
                    uploadImage(selectedImageURI,message);
                } else {
                    String url = Constants.UPLOAD_WALL_POST + "?postImage=" + "NOIMAGE" + "&postMessage=" + message + "&userEmail=" + SharedPrefManager.getInstance(this).getUserEmail();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    response = response.trim();
                                    if (response.equals("okay")){
                                        progressDialog.dismiss();
                                        Toast.makeText(com.example.shivamvk.facebookandroid.NewPostActivity.this, "Post is now live!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(com.example.shivamvk.facebookandroid.NewPostActivity.this, HomeActivity.class));
                                        overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(com.example.shivamvk.facebookandroid.NewPostActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    RequestQueue requestQueue = Volley.newRequestQueue(this);
                    requestQueue.add(stringRequest);
                }
            }
        }
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

    private void uploadImage(Uri selectedImageURI, final String message) {
        StorageReference photoReference = storageReference.child(selectedImageURI.getLastPathSegment());
        photoReference.putFile(selectedImageURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Uri downlaodURI = taskSnapshot.getDownloadUrl();
                Log.i("error",downlaodURI.toString());
                insertInDatabaseWithImage(downlaodURI.toString(), message);
            }
        });
    }

    private void insertInDatabaseWithImage(String s, String message) {
        String url = Constants.UPLOAD_WALL_POST + "?postImage=" + s + "&postMessage=" + message + "&userEmail=" + SharedPrefManager.getInstance(this).getUserEmail();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();
                        if (response.equals("okay")){
                            progressDialog.dismiss();
                            Toast.makeText(com.example.shivamvk.facebookandroid.NewPostActivity.this, "Post is now live!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(com.example.shivamvk.facebookandroid.NewPostActivity.this, HomeActivity.class));
                            overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
