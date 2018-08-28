package com.example.amir.rehave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.amir.rehave.R;
import com.example.amir.rehave.model.CommunityPostModel;

import java.util.ArrayList;

public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.MyViewHolder>{
    private ArrayList<CommunityPostModel> dataSet;
    private  Context context=null;
    private static ReviewListAdapter.ItemClicked clickListener;

    public ReviewListAdapter(ArrayList<CommunityPostModel> data, Context context,ReviewListAdapter.ItemClicked clickListener) {
        this.dataSet = data;
        this.context=context;
        this.clickListener=clickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameView;
        TextView dateView;
        TextView timeView;
        TextView postView;
        Button acceptBtn;
        Button deleteBtn;


        public MyViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.nameView = itemView.findViewById(R.id.name);
            this.dateView = itemView.findViewById(R.id.date);
            this.timeView = itemView.findViewById(R.id.time);
            this.postView = itemView.findViewById(R.id.post);
            this.acceptBtn = itemView.findViewById(R.id.accept);
            this.deleteBtn = itemView.findViewById(R.id.delete);
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
                    ReviewListAdapter.clickListener.onItemClicked(itemView, 1);
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"delete clicked",Toast.LENGTH_SHORT).show();
                    ReviewListAdapter.clickListener.onItemClicked(itemView, 0);

                }
            });
        }
    }



    @Override
    public ReviewListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);

//        view.setOnClickListener(listener);

        ReviewListAdapter.MyViewHolder myViewHolder = new ReviewListAdapter.MyViewHolder(context,view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final ReviewListAdapter.MyViewHolder holder, final int listPosition) {

        TextView nameView = holder.nameView;
        nameView.setText(dataSet.get(listPosition).getName());
        TextView timeView = holder.timeView;
        timeView.setText(dataSet.get(listPosition).getTime());
        TextView dateView = holder.dateView;
        dateView.setText(dataSet.get(listPosition).getDate());
        TextView postView = holder.postView;
        postView.setText(dataSet.get(listPosition).getPost());

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static interface ItemClicked {
        void onItemClicked(View v ,int code);
    }

}
