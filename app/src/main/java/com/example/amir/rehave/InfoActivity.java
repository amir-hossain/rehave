package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.amir.rehave.others.DataModel;
import com.example.amir.rehave.others.ListAdpter;
import com.example.amir.rehave.others.MyData;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(R.string.menuLabel1);
        actionBar.setDisplayHomeAsUpEnabled(true);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.id_[i]
            ));
        }


        adapter = new ListAdpter(data);
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            int index =recyclerView.getChildLayoutPosition(v);


//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,InfoDetailsActivity.class);
            intent.putExtra("index",index);
            context.startActivity(intent);
        }


    }
}
