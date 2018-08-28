package com.example.amir.rehave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.rehave.R;
import com.example.amir.rehave.model.ReplayDataModel;

import java.util.ArrayList;

public class RepalayListAdapter extends RecyclerView.Adapter<RepalayListAdapter.MyViewHolder> {
    private ArrayList<ReplayDataModel> dataSet;
    private Context context=null;
    public RepalayListAdapter(ArrayList<ReplayDataModel> data, Context context) {
        this.dataSet = data;
        this.context=context;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView dateView;
        TextView timeView;
        TextView nameView;
        TextView replayView;




        public MyViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.nameView = itemView.findViewById(R.id.name);
            this.dateView = itemView.findViewById(R.id.date);
            this.timeView = itemView.findViewById(R.id.time);
            this.replayView = itemView.findViewById(R.id.replay);
        }
    }



    @Override
    public RepalayListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.replay_item, parent, false);

//        view.setOnClickListener(listener);

        RepalayListAdapter.MyViewHolder myViewHolder = new RepalayListAdapter.MyViewHolder(context,view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final RepalayListAdapter.MyViewHolder holder, final int listPosition) {

        TextView nameView = holder.nameView;
        nameView.setText(dataSet.get(listPosition).getName());
        TextView timeView = holder.timeView;
        timeView.setText(dataSet.get(listPosition).getTime());
        TextView dateView = holder.dateView;
        dateView.setText(dataSet.get(listPosition).getDate());
        TextView replayView = holder.replayView;
        replayView.setText(dataSet.get(listPosition).getReplay());
    }




    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
