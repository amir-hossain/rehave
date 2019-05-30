package com.example.amir.rehave;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.amir.rehave.databinding.ActivityInfoDetailsBinding;
import com.example.amir.rehave.manager.SharedPrefManager;
import com.example.amir.rehave.model.DataModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityInfoDetails extends AppCompatActivity {
    private ActivityInfoDetailsBinding binding;
    private DataModel data;
    private String userId=SharedPrefManager.getInstance(this).getString(SharedPrefManager.ID_PREF);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_info_details);

        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        data=getIntent().getParcelableExtra("data");
        actionBar.setTitle(Utils.getSectionName(data.getSection()));
        binding.setData(data);



        if(userId!=null){
            binding.commentBox.setVisibility(View.VISIBLE);
            binding.commentBtn.setVisibility(View.VISIBLE);
            String comment=data.getCommentList().get(SharedPrefManager.getInstance(this).getString(SharedPrefManager.ID_PREF));
            binding.commentBox.setText(comment);
        }


    }

    public void saveComment(View view){
        String comment=binding.commentBox.getText().toString().trim();
        if(!comment.isEmpty()){
            String outerTableName=getOuterTableName();
            DatabaseReference reference=FirebaseDatabase.getInstance().getReference("data/"+outerTableName+"/"+data.getPostId()+"/commentList/"+ userId);
            reference.setValue(comment);

            showToast("মন্তব্য সেভ হয়েছে!");

        }else {
            showToast("ফিল্ড খালি আছে!");
        }

    }

    private void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private String getOuterTableName() {
        if(data.getSection()==Constants.Section.ADDICTION.toInt()){
            return "info";
        }else if(data.getSection()==Constants.Section.PROTECTION.toInt()){
            return "pro";
        }
        return "";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
