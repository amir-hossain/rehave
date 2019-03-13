package com.example.amir.rehave.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class UserDatabase {
    private static DatabaseHelper databaseHelper;
    public static RehaveDao getDao(Context context){
        if(databaseHelper ==null){
            databaseHelper = Room.databaseBuilder(context, DatabaseHelper.class,"rehave_db").build();
        }
        return databaseHelper.getDao();
    }
}
