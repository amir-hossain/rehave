package com.example.amir.rehave.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class DataModel implements Parcelable {
    private String id;
    private String title;
    private String post;
    private int section;

    private Map<String,String> commentList;


    public DataModel() {
    }


    public DataModel(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public DataModel(String id, String title, String post) {
        this.title = title;
        this.post = post;
        this.id = id;
    }

    public DataModel(String id, String title, String post, int section, Map<String, String> commentList) {
        this.id = id;
        this.title = title;
        this.post = post;
        this.section = section;
        this.commentList = commentList;
    }

    protected DataModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        post = in.readString();
        section = in.readInt();
        int size=in.readInt();
        this.commentList=new HashMap<>();
        for(int i=0;i<size;i++){
            String key=in.readString();
            String value=in.readString();
            this.commentList.put(key,value);
        }
    }


    public static final Creator<DataModel> CREATOR = new Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel in) {
            return new DataModel(in);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public Map<String, String> getCommentList() {
        return commentList;
    }

    public void setCommentList(Map<String, String> commentList) {
        this.commentList = commentList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(post);
        dest.writeInt(section);
        if(commentList!=null){
            dest.writeInt(commentList.size());
            for(Map.Entry<String,String> entry : commentList.entrySet()){
                dest.writeString(entry.getKey());
                dest.writeString(entry.getValue());
            }
        }
    }
}
