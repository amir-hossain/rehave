package com.example.amir.rehave.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

@Dao
public interface RehaveDao {
    @Insert
    void userRegistration(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComment(OwnCommnet commnet);

    @Query("select * from own_comment where user_id==:userId")
    OwnCommnet getComment (int userId);

    @Query("select * from user where password==:password and phone_or_email==:emailOrPhone")
    User login(String emailOrPhone,String password);

}
