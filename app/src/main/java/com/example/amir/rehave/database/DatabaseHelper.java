package com.example.amir.rehave.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.amir.rehave.R;
import com.example.amir.rehave.model.jsonModel.Root;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;


@Database(entities = {Post.class},version = 1,exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    private static DatabaseHelper INSTANCE;
    private static long[] ids;
    public abstract RehubDao rehubDao();


    public synchronized static DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static DatabaseHelper buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                DatabaseHelper.class,
                "my-database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                String jsonString = getJsonFromFile(context);
                                insertFrom(context,jsonString);

                            }
                        });
                    }
                })
                .build();
    }

    private static void insertFrom(Context context,String jsonString) {
        Root root = JSON.parseObject(jsonString, Root.class);
        ArrayList<Post> archPost=getPostObjFrom(root.getData().getArch());
        ArrayList<Post> infoPost=getPostObjFrom(root.getData().getInfo());
        ArrayList<Post> proPost=getPostObjFrom(root.getData().getPro());

        mergeAndInsertDatabase(context,archPost,infoPost,proPost);
    }

    private static void mergeAndInsertDatabase(Context context,ArrayList<Post> archPost, ArrayList<Post> infoPost, ArrayList<Post> proPost) {
        ArrayList<Post> allPost=new ArrayList<>();
        allPost.addAll(archPost);
        allPost.addAll(infoPost);
        allPost.addAll(proPost);
        ids=getInstance(context).rehubDao().insertAllPost(allPost);
    }

    private static ArrayList<Post> getPostObjFrom(String json) {
        ArrayList<Post> posts=new ArrayList<>();
        JSONObject ii= (JSONObject) JSON.parse(json);
        for (Object o:ii.values()) {
            JSONObject obj= (JSONObject) o;
            String id=obj.getString("id");
            String post=obj.getString("post");
            int section=obj.getIntValue("section");
            String title=obj.getString("title");
            posts.add(new Post(id,post,section,title));
        }

        return posts;
    }

    private static String getJsonFromFile(Context context){

        String json = null;
        try {

            InputStream is = context.getResources().openRawResource(R.raw.offline);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }
}
