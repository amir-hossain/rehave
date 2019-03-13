package com.example.amir.rehave.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class MainFragmentData implements Parcelable {

    private String postId;

    private String title;

    private String section;

    private String post;

    private Map<String,String> commentList;

//    need to fetch firebase data
    public MainFragmentData() {
    }

    public MainFragmentData(String id, String title, String section) {
        this.postId = id;
        this.title = title;
        this.section = section;
    }

    protected MainFragmentData(Parcel in) {
        postId = in.readString();
        title = in.readString();
        section = in.readString();
        post=in.readString();
        int size=in.readInt();
        this.commentList=new HashMap<>();
        for(int i=0;i<size;i++){
            String key=in.readString();
            String value=in.readString();
            this.commentList.put(key,value);
        }

    }

    public static final Creator<MainFragmentData> CREATOR = new Creator<MainFragmentData>() {
        @Override
        public MainFragmentData createFromParcel(Parcel in) {
            return new MainFragmentData(in);
        }

        @Override
        public MainFragmentData[] newArray(int size) {
            return new MainFragmentData[size];
        }
    };

    public String getPostId() {
        return postId;
    }

    public void setPostId(String id) {
        this.postId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Map<String, String> getCommentList() {
        return commentList;
    }

    public void setCommentList(Map<String, String> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(postId);
        dest.writeString(title);
        dest.writeString(section);
        dest.writeString(post);
        if(commentList!=null){
            dest.writeInt(commentList.size());
            for(Map.Entry<String,String> entry:commentList.entrySet()){
                dest.writeString(entry.getKey());
                dest.writeString(entry.getValue());
            }
        }

    }
}
