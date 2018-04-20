package com.example.amir.rehave;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.amir.rehave.others.DataModel;
import com.example.amir.rehave.others.ListAdpter;

import java.util.ArrayList;

public class ProtectionActivity extends AppCompatActivity {
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
        actionBar.setTitle(R.string.menuLabel2);
        actionBar.setDisplayHomeAsUpEnabled(true);

        myOnClickListener = new ProtectionActivity.MyOnClickListener(this);

        recyclerView =findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        data.add(new DataModel(getResources().getString(R.string.title3),"sdsdsds"));
        data.add(new DataModel(getResources().getString(R.string.title4),"sdsdsds"));
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
            String key=data.get(index).getId();


//            Toast.makeText(context,index+" clicked",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(context,InfoDetailsActivity.class);
            intent.putExtra("key",key);
            context.startActivity(intent);
        }


    }
}
