package com.example.amir.rehave.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amir.rehave.InfoActivity;
import com.example.amir.rehave.R;

import java.util.ArrayList;

public class AdminListAdapter extends RecyclerView.Adapter<AdminListAdapter.MyViewHolder>{
    private ArrayList<DataModel> dataSet;
    private final Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView section;
        Button edit;
        Button delete;


        public MyViewHolder(final Context context,View itemView) {
            super(itemView);
            this.title =itemView.findViewById(R.id.title_view);
            this.section =itemView.findViewById(R.id.section_view);
            this.edit =itemView.findViewById(R.id.edit);
            this.delete =itemView.findViewById(R.id.delete);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Edit clicked",Toast.LENGTH_SHORT).show();
                }
            });
            
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"delete clicked",Toast.LENGTH_SHORT).show();
                    
                }
            });
        }
    }

    public AdminListAdapter(ArrayList<DataModel> data, Context context) {
        this.dataSet = data;
        this.context=context;
    }

    @Override
    public AdminListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.admin_item, parent, false);

        view.setOnClickListener(InfoActivity.myOnClickListener);

        AdminListAdapter.MyViewHolder myViewHolder = new AdminListAdapter.MyViewHolder(context,view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final AdminListAdapter.MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.title;
        textViewName.setText(dataSet.get(listPosition).getTitle());
        TextView section = holder.section;
        section.setText("( "+dataSet.get(listPosition).getSection()+" )");

    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
