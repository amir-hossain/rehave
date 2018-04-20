package com.example.amir.rehave.others;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.rehave.InfoActivity;
import com.example.amir.rehave.R;

import java.util.ArrayList;

public class ListAdpter extends RecyclerView.Adapter<ListAdpter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName =itemView.findViewById(R.id.textViewName);
        }
    }

    public ListAdpter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);

        view.setOnClickListener(InfoActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;


        textViewName.setText(dataSet.get(listPosition).getTitle());

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
