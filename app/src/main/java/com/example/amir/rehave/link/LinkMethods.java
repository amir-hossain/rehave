package com.example.amir.rehave.link;

import android.content.Context;
import android.util.Log;

import com.example.amir.rehave.Constants;
import com.example.amir.rehave.data.DataListeners;
import com.example.amir.rehave.data.DataMethods;
import com.example.amir.rehave.database.DatabaseHelper;
import com.example.amir.rehave.database.Post;
import com.example.amir.rehave.database.RehubDao;
import com.example.amir.rehave.model.DataModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class LinkMethods extends Constants implements DataListeners.DataTableListener {
    private static RehubDao rehubDao;
    private static LinkMethods instance = new LinkMethods();

    private LinkListeners.DataTableListener dataTableListener;

    private LinkMethods() {
    }

    public static LinkMethods getInstance() {
        return instance;
    }


    public void setDataTableListener(String rootTable, LinkListeners.DataTableListener listener) {

        DataMethods.setDataTableListener(rootTable, LinkMethods.this);
        this.dataTableListener = listener;
    }

    public void setDataTableListener(Context context, final int sectionId, final LinkListeners.DataTableListener listener) {
        if (rehubDao == null) {
            initializeDatabase(context);
        }

        Observable.create(new ObservableOnSubscribe<List<Post>>() {
            List<Post> posts;

            @Override
            public void subscribe(ObservableEmitter<List<Post>> emitter) throws Exception {
                final ObservableEmitter<List<Post>> finalEmmitter = emitter;
                if (sectionId == Section.ALL.toInt()) {
                    posts = rehubDao.getAllPostWithoutArchive();
                } else if (sectionId == Section.ADDICTION.toInt()) {
                    posts = rehubDao.getAllAddictionInfoPost();
                    emitter.onNext(posts);
                } else if (sectionId == Section.PROTECTION.toInt()) {
                    posts = rehubDao.getAllProtectionInfoPost();
                    emitter.onNext(posts);
                } else {
                    posts = new ArrayList<>();
                    final FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("data/arch");
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot section : dataSnapshot.getChildren()) {
                                for (DataSnapshot item : section.getChildren()) {
                                    for(DataSnapshot comment:item.getChildren()){
                                        Log.d("sdsd","sdsd");
                                    }
                                    posts.add(item.getValue(Post.class));
                                }
                            }
                            finalEmmitter.onNext(posts);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Post>>() {
                    @Override
                    public void accept(List<Post> o) throws Exception {
                        List<DataModel> datas = convertPostToDataModel(o);

                        if (sectionId == Section.ALL.toInt()) {

                            listener.listenDatable(getRandomTenData(datas));

                        } else {
                            listener.listenDatable(datas);
                        }

                    }
                });
    }

    private List<DataModel> convertPostToDataModel(List<Post> posts) {
        ArrayList<DataModel> dataModels = new ArrayList<>();
        for (Post post : posts) {
            dataModels.add(new DataModel(post.getPostId(), post.getTitle(), post.getPost(), post.getSection(), null));
        }
        return dataModels;
    }

    public void initializeDatabase(Context context) {

        rehubDao = DatabaseHelper.getInstance(context).rehubDao();
    }


    @Override
    public void listenDataTable(DataSnapshot snapshot) {

        List<DataModel> datas = new ArrayList<>();
        DataModel mainFragmentData;

        for (DataSnapshot childSnap : snapshot.getChildren()) {
            if (!childSnap.getKey().equals("arch")) {
                for (DataSnapshot grandChild : childSnap.getChildren()) {

                    mainFragmentData = grandChild.getValue(DataModel.class);
//                    mainFragmentData.setPostId(grandChild.getKey());
                    datas.add(mainFragmentData);
                }
            }


        }


        datas = getRandomTenData(datas);

        dataTableListener.listenDatable(datas);


    }

    private List<DataModel> getRandomTenData(List<DataModel> datas) {
        List<DataModel> ramdomData;
        if (datas.size() > 10) {
            ramdomData = new ArrayList<>();

//       all data now random

            Collections.shuffle(datas);

//        take Ten data

            for (int i = 0; i < 10; i++) {

                ramdomData.add(datas.get(i));
            }
        } else {
            ramdomData = datas;
        }


        return ramdomData;
    }
}
