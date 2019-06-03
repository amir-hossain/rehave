package com.example.amir.rehave.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    

    private String comment;


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

    public DataModel(String postId, String title, String post, int section, String comment) {
        this.postId = postId;
        this.title = title;
        this.post = post;
        this.section = section;
        this.comment = comment;
    }

    protected DataModel(Parcel in) {
        postId = in.readString();
        title = in.readString();
        post = in.readString();
        section = in.readInt();
        comment =in.readString();
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

    public String getComment() {
        return comment;
    }

    public void setCommentList(String comment) {
        this.comment = comment;
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
        dest.writeString(comment);
    }
}
