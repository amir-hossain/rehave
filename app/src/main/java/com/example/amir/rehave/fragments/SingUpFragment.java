package com.example.amir.rehave.fragments;

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

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SingUpFragment extends BaseFragment implements View.OnClickListener{
    EditText userName;
    EditText passwordField;
    EditText centerField;

    private RegistrationCompleteListener registrationCompleteListener;
    private View rootView;
    private boolean isCheckboxChecked=false;

    public static SingUpFragment newInstance(RegistrationCompleteListener registrationCompleteListener) {
        SingUpFragment fragment = new SingUpFragment();
        fragment.registrationCompleteListener =registrationCompleteListener;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.signup_fragment, container, false);

        userName=rootView.findViewById(R.id.user_name);
        passwordField =rootView.findViewById(R.id.user_name);
        centerField =rootView.findViewById(R.id.password);
        final Button singUp=rootView.findViewById(R.id.button);
        CheckBox checkBox=rootView.findViewById(R.id.checkbox);
        singUp.setOnClickListener(SingUpFragment.this);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isCheckboxChecked=isChecked;
                if(isChecked){
                    singUp.setBackgroundColor(getResources().getColor(R.color.darkTeal));

                }else {
                    singUp.setBackgroundColor(getResources().getColor(R.color.color_white));


                }
            }
        });

        return rootView;

    }

    @Override
    public void onClick(View v) {
        if(isCheckboxChecked){
            String name=userName.getText().toString();
            String center= centerField.getText().toString();
            String password= passwordField.getText().toString();

            postData(name,center,password);
//        saveOfline(name,pass,userName);
        }else {
            showToast("দয়া করে শর্ত মানুন",Toast.LENGTH_LONG);
        }

    }

    private void saveOfline(final String name, final String pass, final String phoneEmail) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
//                dao.userRegistration(new JSONPost(name,userName,pass));
                emitter.onNext("sucessfully registered");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        showToast(s, Toast.LENGTH_LONG);
                        registrationCompleteListener.OnRegistrationComplete();
                    }
                });

    }

    private void postData(String name,String center,String password) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String path="auth/";
        DatabaseReference tempRef=database.getReference(path);
        String key=tempRef.push().getKey();
        DatabaseReference mainRef=database.getReference(path+key+"/");
        SignUpModel model=new SignUpModel(key,name,center,password);
        mainRef.setValue(model,new
                DatabaseReference.CompletionListener() {

                    @Override
                    public void onComplete(DatabaseError databaseError,
                                           DatabaseReference databaseReference) {
                        showToast("রেজিস্টেশন সম্পুর্ন হয়েছে !", Toast.LENGTH_SHORT);
                        registrationCompleteListener.OnRegistrationComplete();
                    }
                });

    }

    private void showToast(String s, int lengthShort) {
        Toast.makeText(getContext(), s, lengthShort).show();
    }

    public interface RegistrationCompleteListener{
        void OnRegistrationComplete();
    }


}