package com.example.amir.rehave.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database(entities = {User.class,OwnCommnet.class},version = 1)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract RehaveDao getDao();
}
