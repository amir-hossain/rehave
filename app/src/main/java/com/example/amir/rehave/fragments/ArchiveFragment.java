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

public class ArchiveFragment extends Fragment implements LinkListeners.DataTableListener {
    private static RecyclerView.Adapter adapter;

    private RecyclerView.LayoutManager layoutManager;

    private static RecyclerView recyclerView;

    private static View.OnClickListener myOnClickListener;

    private RecyclerView view;

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
        ArchiveListAdapter archiveListAdapter = new ArchiveListAdapter(ArchiveFragment.this,datas);
        view.setAdapter(archiveListAdapter);
    }
}
