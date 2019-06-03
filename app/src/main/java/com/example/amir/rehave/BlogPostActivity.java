package com.example.amir.rehave;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.amir.rehave.model.DataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class BlogPostActivity extends AppCompatActivity{

    int subject;
    EditText titleView;
    EditText postView;

    @BindView(R.id.section)
    Spinner sectionSpinner;

    @BindView(R.id.sub_section)
    Spinner subSectionSpinner;

    int selectedPosition;
    int subSelectedPosition;
    String path=null;
    int section;

    private static final String REGEXP_ID_PATTERN = "(?i)https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube(?:-nocookie)?\\.com" +
            "\\S*?[^\\w\\s-])([\\w-]{11})(?=[^\\w-]|$)(?![?=&+%\\w.-]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w.-]*";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_post);
        ButterKnife.bind(this);
        if(getIntent().getExtras()!=null){
            path=getIntent().getExtras().getString("path");
        }

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.postLabel);
        if(path!=null){
            actionBar.setTitle(R.string.editLabel);
        }
        actionBar.setDisplayHomeAsUpEnabled(true);


        List<String> section = new ArrayList<>();
        section.add(getResources().getString(R.string.adiction_information));
        section.add(getResources().getString(R.string.relapse_protection));
        section.add(getResources().getString(R.string.archive));
        creatSpinner(sectionSpinner,section);

        List<String> subSection = new ArrayList<>();
        subSection.add("Video");
        subSection.add("Audio");
        subSection.add("Book");
        subSection.add("Image");
        subSection.add("Sharing");
        subSection.add("Tools");
        creatSpinner(subSectionSpinner,subSection);


        titleView=findViewById(R.id.title);
        postView=findViewById(R.id.post);
        Button postBtn=findViewById(R.id.post_button);
        if(path!=null){
            postBtn.setText(R.string.editLabel);
        }
        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=titleView.getText().toString().trim();
                String post=postView.getText().toString().trim();
                postData(title,post,subject);

            }
        });
        if(path!=null){
            getData();
        }


    }


  @OnItemSelected(R.id.section)
  public void spinnerSelected(int position){
      selectedPosition=position;
      if(position==2){
          subSectionSpinner.setVisibility(View.VISIBLE);
      }else {
          subSectionSpinner.setVisibility(View.GONE);
      }
  }

    @OnItemSelected(R.id.sub_section)
    public void subSectionSelected(int position){
        subSelectedPosition =position;
    }

    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//               Log.d("xxxxxxxxxx",dataSnapshot.getValue().toString());
                DataModel value=dataSnapshot.getValue(DataModel.class);
                titleView.setText(value.getTitle());
                postView.setText(value.getPost());
                if(value.getSection()==Constants.Section.PROTECTION.toInt()){
                    sectionSpinner.setSelection(Constants.Section.PROTECTION.toInt());
                }else if(value.getSection()==Constants.Section.ARCHIVE.toInt()){
                    sectionSpinner.setSelection(Constants.Section.ARCHIVE.toInt());
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }

    private void postData(final String title, String post, int subject) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path="data/info/";
        section=1;
        if(selectedPosition==1){
            path="data/pro/";
            section=2;
        }else if(selectedPosition==2){
            path="data/arch/";
            if(subSelectedPosition==0){
                path=path+"/video/";
                section=3;
            }else if(subSelectedPosition==1){
                path=path+"/audio/";
                section=4;
            }else if(subSelectedPosition==2){
                path=path+"/book/";
                section=5;
            }else if(subSelectedPosition==3){
                path=path+"/image/";
                section=6;
            }else if(subSelectedPosition==4){
                path=path+"/sharing/";
                section=7;
            }else if(subSelectedPosition==5){
                path=path+"/tools/";
                section=8;
            }
        }

        DatabaseReference tempRef = database.getReference(path);

        if(section==3){
                try {
                  extractVideoId(post);
                    String key=tempRef.push().getKey();
                    DatabaseReference mainRef=database.getReference(path+key);
                    DataModel model=new DataModel(key,title,post,section,null);
                    mainRef.setValue(model,new
                            DatabaseReference.CompletionListener() {

                                @Override
                                public void onComplete(DatabaseError databaseError,
                                                       DatabaseReference databaseReference) {
                                    Toast.makeText(BlogPostActivity.this,"posted sucessfully",Toast.LENGTH_SHORT).show();
                                    titleView.setText("");
                                    postView.setText("");
                                    Utils.notification++;

                                }
                            });
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"invalid youtube url",Toast.LENGTH_SHORT).show();
                }

        }else{
            String key=tempRef.push().getKey();
            DatabaseReference mainRef=database.getReference(path+key);
            DataModel model=new DataModel(key,title,post,section,null);
            mainRef.setValue(model,new
                    DatabaseReference.CompletionListener() {

                        @Override
                        public void onComplete(DatabaseError databaseError,
                                               DatabaseReference databaseReference) {
                            Toast.makeText(BlogPostActivity.this,"posted sucessfully",Toast.LENGTH_SHORT).show();
                            titleView.setText("");
                            postView.setText("");
                            Utils.notification++;

                        }
                    });
        }

    }

    private String extractVideoId(String url) {
        final Pattern pattern = Pattern.compile(REGEXP_ID_PATTERN);
        final Matcher matcher = pattern.matcher(url);
        if (matcher.matches()) {
            return matcher.group(1);
        }

        throw new IllegalArgumentException("Cannot extract video id from url");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      if(item.getItemId()==android.R.id.home){
          onBackPressed();
      }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void creatSpinner(Spinner spinner, List<String> data) {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
}
