package com.example.amir.rehave;

import android.app.Application;
import android.util.Log;

import com.example.amir.rehave.manager.SharedPrefManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
   /*     boolean notIntialLunch=SharedPrefManager.getInstance(this).getBoolean(SharedPrefManager.IS_NOT_INTIAL_LUNCH);
        Observable observable=Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                        DatabaseReference reference=database.getReference("totalItems");
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Integer total= (Integer) dataSnapshot.getValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.d("dfdf","dfdf");
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        if(notIntialLunch){
            observable.subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer newTotal) throws Exception {
                    int previousTotal=SharedPrefManager.getInstance(getApplicationContext())
                            .getint(SharedPrefManager.TOTAL_PREF);
                    if(previousTotal<newTotal){
                        Utils.notification=newTotal-previousTotal;
                    }
                }
            });
        }else {
            observable.subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    SharedPrefManager.getInstance(getApplicationContext())
                            .setInt(SharedPrefManager.TOTAL_PREF,integer);
                }
            });
        }*/
    }
}
