package com.example.amir.rehave.data;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataMethods {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void setDataTableListener(final DataListeners.DataTableListener dataTableListener){
        database.getReference("data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                dataTableListener.listenDataTable(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("d","d");
            }
        });
    }
}
