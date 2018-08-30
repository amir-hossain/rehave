package com.example.amir.rehave.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.R;
import com.example.amir.rehave.model.SignUpModel;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SingUpFragment extends Fragment implements View.OnClickListener{
    EditText userName;
    EditText phonEmail;
    EditText password;
    private View rootView;

    public static SingUpFragment newInstance() {
        SingUpFragment fragment = new SingUpFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.signup_fragment, container, false);



        userName=rootView.findViewById(R.id.phone_email);
        phonEmail=rootView.findViewById(R.id.phone_email);
        password=rootView.findViewById(R.id.password);
        final Button singUp=rootView.findViewById(R.id.button);
        CheckBox checkBox=rootView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    singUp.setBackgroundColor(getResources().getColor(R.color.darkTeal));
                    singUp.setOnClickListener(SingUpFragment.this);
                }else {
                    singUp.setBackgroundColor(getResources().getColor(R.color.color_white));

                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v) {
        String name=userName.getText().toString();
        String pass=password.getText().toString();
        String phoneEmail=phonEmail.getText().toString();

        postData(name,pass,phoneEmail);
    }

    private void postData(String name,String pass,String phoneEmail) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path="auth/";
        DatabaseReference tempRef=database.getReference(path);
        String key=tempRef.push().getKey();
        DatabaseReference mainRef=database.getReference(path+key+"/");
        SignUpModel model=new SignUpModel(key,name,pass,phoneEmail);
        mainRef.setValue(model,new
                DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        Toast.makeText(getContext(),"sucessfully registered",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getContext(),LoginFragment.class));
                    }
                });



    }
}