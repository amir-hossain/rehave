package com.example.amir.rehave.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.amir.rehave.Constants;
import com.example.amir.rehave.R;
import com.example.amir.rehave.adapter.ArchiveListAdapter;
import com.example.amir.rehave.link.LinkListeners;
import com.example.amir.rehave.link.LinkMethods;
import com.example.amir.rehave.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ArchiveFragment extends Fragment implements LinkListeners.DataTableListener, ArchiveListAdapter.onCategoryClickListener {
    ArchiveListAdapter archiveListAdapter;

    private RecyclerView view;

    public static int VIDEO=3;
    public static int AUDIO=4;
    public static int BOOK=5;
    public static int IMAGE=6;
    public static int SHARING=7;
    public static int TOOLS=8;

    private ArrayList<DataModel> videoData=new ArrayList<>();
    private ArrayList<DataModel> audioData=new ArrayList<>();
    private ArrayList<DataModel> imageData=new ArrayList<>();
    private ArrayList<DataModel> bookData=new ArrayList<>();
    private ArrayList<DataModel> shareData=new ArrayList<>();
    private ArrayList<DataModel> toolsData=new ArrayList<>();

    public static ArchiveFragment newInstance() {
        ArchiveFragment fragment = new ArchiveFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = (RecyclerView) inflater.inflate(R.layout.fragment_archive, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        view.setLayoutManager(linearLayoutManager);

        getData();

        return view;
    }



    private void getData(){

        LinkMethods.getInstance().setDataTableListener(getContext(), Constants.Section.ARCHIVE.toInt(),this);

    }

    @Override
    public void listenDatable(List<DataModel> datas) {
        catagorizeData(datas);
        archiveListAdapter = new ArchiveListAdapter(ArchiveFragment.this,videoData,this);
        view.setAdapter(archiveListAdapter);
    }

    private void catagorizeData(List<DataModel> datas) {
        addNullForHeader();
        for(DataModel data:datas){
            if(data.getSection()==VIDEO){
                videoData.add(data);
            }else if(data.getSection()==AUDIO){
                audioData.add(data);
            }else if(data.getSection()==BOOK){
                bookData.add(data);
            }else if(data.getSection()==IMAGE){
                imageData.add(data);
            }else if(data.getSection()==SHARING){
                shareData.add(data);
            }else if(data.getSection()==TOOLS){
                toolsData.add(data);
            }
        }
    }

    private void addNullForHeader() {
        videoData.add(0,null);
        audioData.add(0,null);
        bookData.add(0,null);
        imageData.add(0,null);
        shareData.add(0,null);
        toolsData.add(0,null);

    }

    @Override
    public void onCategoryClick(int dataType) {
        ArrayList<DataModel> data;
        if(dataType==VIDEO){
            data=videoData;
        }else if(dataType==AUDIO){
            data=audioData;
        }else if(dataType==IMAGE){
            data=imageData;
        }else if(dataType==BOOK){
            data=bookData;
        }else if(dataType==SHARING){
            data=shareData;
        }else {
            data=toolsData;
        }
        archiveListAdapter.setData(data);
    }
}
