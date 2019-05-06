package com.example.amir.rehave.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import com.example.amir.rehave.ItemClickListener;
import com.example.amir.rehave.R;
import com.example.amir.rehave.Utils;
import com.example.amir.rehave.model.DataModel;
import com.example.amir.rehave.model.MainFragmentData;

import java.util.List;

public class MainFragmentAdapter extends RecyclerView.Adapter<MainFragmentAdapter.MyViewHolder> {

    private List<DataModel> dataList;
    private ItemClickListener listener;



    public MainFragmentAdapter(List<DataModel> albumList, ItemClickListener listener) {

        this.dataList = albumList;

        this.listener = listener;

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, section;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);

            section = view.findViewById(R.id.section);


        }
    }


    @Override
    public MainFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_product_list_card, parent, false);

        return new MainFragmentAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainFragmentAdapter.MyViewHolder holder, final int position) {
        final DataModel data = dataList.get(position);

        holder.title.setText(data.getTitle());

        holder.section.setText(Utils.getSectionName(data.getSection()));




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.itemClick(data);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

}
