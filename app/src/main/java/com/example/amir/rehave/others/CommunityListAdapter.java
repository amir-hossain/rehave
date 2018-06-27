package com.example.amir.rehave.others;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.amir.rehave.R;

import java.util.ArrayList;

public class CommunityListAdapter extends RecyclerView.Adapter<CommunityListAdapter.MyViewHolder> {
    private ArrayList<CommunityPostModel> dataSet;
    private Context context=null;
    private static CommunityListAdapter.ItemClicked clickListener;
    public CommunityListAdapter(ArrayList<CommunityPostModel> data, Context context,CommunityListAdapter.ItemClicked clickListener) {
        this.dataSet = data;
        this.context=context;
        this.clickListener=clickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleView;
        TextView nameView;
        TextView dateView;
        TextView timeView;
        TextView commentView;
        TextView postView;
        CardView cardView;


        public MyViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.titleView=itemView.findViewById(R.id.title);
            this.nameView = itemView.findViewById(R.id.name);
            this.dateView = itemView.findViewById(R.id.date);
            this.timeView = itemView.findViewById(R.id.time);
            this.commentView = itemView.findViewById(R.id.comment);
            this.postView = itemView.findViewById(R.id.post);
            this.cardView = itemView.findViewById(R.id.card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
                    CommunityListAdapter.clickListener.onItemClicked(itemView);
                }
            });



        }
    }



    @Override
    public CommunityListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.community_item, parent, false);

//        view.setOnClickListener(listener);

        CommunityListAdapter.MyViewHolder myViewHolder = new CommunityListAdapter.MyViewHolder(context,view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final CommunityListAdapter.MyViewHolder holder, final int listPosition) {
        TextView titleView = holder.titleView;
        String title=dataSet.get(listPosition).getTitle();
        if(title.equals("")){
            titleView.setText(context.getString(R.string.untitled));

        }else{
            titleView.setText(title);
        }

        TextView nameView = holder.nameView;
        nameView.setText(dataSet.get(listPosition).getName());
        TextView timeView = holder.timeView;
        timeView.setText(dataSet.get(listPosition).getTime());
        TextView dateView = holder.dateView;
        dateView.setText(dataSet.get(listPosition).getDate());
        TextView postView = holder.postView;
        postView.setText(dataSet.get(listPosition).getPost());
        TextView countView = holder.commentView;
        String count=dataSet.get(listPosition).getCommentCount();
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
        void onItemClicked(View v);
    }
}
