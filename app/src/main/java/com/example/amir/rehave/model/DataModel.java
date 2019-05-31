package com.example.amir.rehave.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class DataModel implements Parcelable {

    @Expose
    @SerializedName("postId")
    private String postId;
    @Expose
    private String title;
    @Expose
    private String post;
    @Expose
    private int section;

    private Map<String,String> commentList;


    public DataModel() {
    }


    public DataModel(String title, String postId) {
        this.title = title;
        this.postId = postId;
    }

    public DataModel(String postId, String title, String post) {
        this.title = title;
        this.post = post;
        this.postId = postId;
    }

    public DataModel(String postId, String title, String post, int section, Map<String, String> commentList) {
        this.postId = postId;
        this.title = title;
        this.post = post;
        this.section = section;
        this.commentList = commentList;
    }

    protected DataModel(Parcel in) {
        postId = in.readString();
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

    @SerializedName("postId")
    public String getPostId() {
        return postId;
    }

    @SerializedName("postId")
    public void setPostId(String postId) {
        this.postId = postId;
    }

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
        return postId;
    }

    public void setId(String postId) {
        this.postId = postId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postId);
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
