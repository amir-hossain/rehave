package com.example.amir.rehave.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codewaves.youtubethumbnailview.ThumbnailLoader;
import com.codewaves.youtubethumbnailview.ThumbnailView;
import com.example.amir.rehave.InfoActivity;
import com.example.amir.rehave.R;

import java.util.ArrayList;

public class ArchiveListAdapter extends RecyclerView.Adapter<ArchiveListAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ThumbnailView thumbnailView;





        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName =itemView.findViewById(R.id.textViewName);
            thumbnailView=itemView.findViewById(R.id.thumbnail);
        }
    }

    public ArchiveListAdapter(Context context, ArrayList<DataModel> data) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public ArchiveListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        ThumbnailLoader.initialize(context);
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.archive_item, parent, false);

        view.setOnClickListener(InfoActivity.myOnClickListener);

        ArchiveListAdapter.MyViewHolder myViewHolder = new ArchiveListAdapter.MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final ArchiveListAdapter.MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        ThumbnailView thumbnailView=holder.thumbnailView;

        textViewName.setText(dataSet.get(listPosition).getTitle());
        thumbnailView.loadThumbnail(dataSet.get(listPosition).getPost());

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
