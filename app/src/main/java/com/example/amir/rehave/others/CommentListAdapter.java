package com.example.amir.rehave.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.amir.rehave.R;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.MyViewHolder> {
    private ArrayList<CommentDataModel> dataSet;
    private Context context=null;
    private static CommentListAdapter.ItemClicked clickListener;
    public CommentListAdapter(ArrayList<CommentDataModel> data, Context context, CommentListAdapter.ItemClicked clickListener) {
        this.dataSet = data;
        this.context=context;
        this.clickListener=clickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView dateView;
        TextView timeView;
        TextView nameView;
        TextView countView;
        RelativeLayout replayBtn;



        public MyViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.nameView = itemView.findViewById(R.id.name);
            this.dateView = itemView.findViewById(R.id.date);
            this.timeView = itemView.findViewById(R.id.time);
            this.replayBtn = itemView.findViewById(R.id.replay);
            this.countView = itemView.findViewById(R.id.count);
            replayBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
                    CommentListAdapter.clickListener.onItemClicked(itemView, 1);
                }
            });



        }
    }



    @Override
    public CommentListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false);

//        view.setOnClickListener(listener);

        CommentListAdapter.MyViewHolder myViewHolder = new CommentListAdapter.MyViewHolder(context,view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final CommentListAdapter.MyViewHolder holder, final int listPosition) {

        TextView nameView = holder.nameView;
        nameView.setText(dataSet.get(listPosition).getName());
        TextView timeView = holder.timeView;
        timeView.setText(dataSet.get(listPosition).getTime());
        TextView dateView = holder.dateView;
        dateView.setText(dataSet.get(listPosition).getDate());
        TextView countView = holder.countView;
        String count=dataSet.get(listPosition).getReplayCount();
        if (count.equals("0")){
            countView.setVisibility(View.GONE);
        }else {
            countView.setText(count);
        }


}




    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static interface ItemClicked {
        void onItemClicked(View v ,int code);
    }
}