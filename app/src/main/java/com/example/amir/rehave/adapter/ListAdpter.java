package com.example.amir.rehave.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.rehave.ItemClickListener;
import com.example.amir.rehave.R;
import com.example.amir.rehave.model.DataModel;

import java.util.List;

public class ListAdpter extends RecyclerView.Adapter<ListAdpter.MyViewHolder> {
    private int numberOfLines;
    private List<DataModel> dataSet;
    private ItemClickListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        View itemView;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName =itemView.findViewById(R.id.textViewName);
            this.itemView=itemView;
            this.textViewName.setLines(numberOfLines);


        }
    }

    public ListAdpter(List<DataModel> data, ItemClickListener listener,int numberOfLines) {
        this.dataSet = data;
        this.listener=listener;
        this.numberOfLines=numberOfLines;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;


        textViewName.setText(dataSet.get(listPosition).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(dataSet.get(listPosition));
            }
        });

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
