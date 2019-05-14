package com.example.amir.rehave.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amir.rehave.R;
import com.example.amir.rehave.manager.SharedPrefManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainFragment extends Fragment{

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @BindView(R.id.day_counter)
    TextView dayView;

    @BindView(R.id.slogan)
    TextView slogan;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,view);
        serDayCounter();
        return view;
    }

    private void serDayCounter() {
        String date=SharedPrefManager.getInstance(getContext()).getString(SharedPrefManager.DATE_PREF);
        int day=0;
        if(date==null){
            day=1;
            date=formatter.format(new Date());
            SharedPrefManager.getInstance(getContext()).setString(SharedPrefManager.DATE_PREF,date);
        }else {
            try {
                Date savedDate=formatter.parse(date);
                long diffInMillies = Math.abs(savedDate.getTime() - new Date().getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                day= (int) diff;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        dayView.setText((day+1)+"");
}

}

