package com.example.amir.rehave.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.amir.rehave.model.DataModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RehubDao {
    @Insert
    long[] insertPost(Post... posts);

    @Insert
    long[] insertAllPost(ArrayList<Post> posts);

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void saveComment(Comment comment);

//    @Query("select * from comment")
//    Comment getComment (int userId);

//    @Query("select * from post where post_id==:postId")
//    JSONPost getPost (int postId);

    @Query("select * from post where section !=3")
    List<Post> getAllPostWithoutArchive();

    @Query("select * from post where section ==1")
    List<Post> getAllAddictionInfoPost();

    @Query("select * from post where section ==2")
    List<Post> getAllProtectionInfoPost();

    @Query("select * from post where section ==3")
    List<Post> getAllArchiveInfoPost();


}
