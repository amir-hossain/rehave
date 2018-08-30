package com.example.amir.rehave.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.amir.rehave.AdminActivity;
import com.example.amir.rehave.MainActivity;
import com.example.amir.rehave.R;
import com.example.amir.rehave.model.SignUpModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginFragment extends Fragment {
    String phoneEmail;
    String pass;
    EditText phoneEmailField;
    EditText password;
    private View rootView;
    ArrayList<SignUpModel> singUpDatas =new ArrayList<>();

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.login_fragment, container, false);
        phoneEmailField =rootView.findViewById(R.id.phone_email);
        password=rootView.findViewById(R.id.password);

        getData();

        Button loginButton=rootView.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneEmail= phoneEmailField.getText().toString().trim();
                pass=password.getText().toString().trim();
//                Log.d("xxx",pass);
                check();

            }
        });

        Button singupButton=rootView.findViewById(R.id.singup_button);
        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingUpFragment nextFrag= new SingUpFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, nextFrag,"findThisFragment")
                        .addToBackStack(null)
                        .commit();

            }
        });

        return rootView;
    }

    private void check() {
        if(singUpDatas!=null){
            boolean result=false;
            if(phoneEmail.equals("admin") && pass.equals("admin")){
                saveToPreference("1","Admin");
                startActivity(new Intent(getContext().getApplicationContext(),AdminActivity.class));
                result= true;
            }else{
                for(int i=0;i<singUpDatas.size();i++){
                    String dataBasephoneEmail=singUpDatas.get(i).getPhoneEmail();
                    String dataBasePass=singUpDatas.get(i).getPassword();
                    String id=singUpDatas.get(i).getId();
                    String name=singUpDatas.get(i).getName();
                    if(phoneEmail.equals(dataBasephoneEmail) && pass.equals(dataBasePass)){
                        saveToPreference(id,name);
                        startActivity(new Intent(getContext(),MainActivity.class));

                        result= true;
                        break;
                    }else{
                        result= false;
                    }
                }
            }


            if(!result){
                Toast.makeText(getContext(),R.string.loginErrorMessage,Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void saveToPreference(String id,String name) {
        SharedPreferences mSharedPreferences = getContext().getSharedPreferences("id", Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString("id",id);
        mEditor.putString("name",name);
        mEditor.apply();
    }


    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("auth");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    SignUpModel value=snap.getValue(SignUpModel.class);
//                        Log.d("Fire value", "Value is: " + value.getName());
                    singUpDatas.add(new SignUpModel(value.getId(),value.getName(),value.getPassword(),value.getPhoneEmail()));
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Fire value", "Failed to read value.", error.toException());
            }
        });

    }


}
