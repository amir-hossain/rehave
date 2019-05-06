package com.example.amir.rehave.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;


public class NoInternetConnection extends Fragment {


    public NoInternetConnection() {
        // Required empty public constructor
    }

    public static NoInternetConnection newInstance() {
        return new NoInternetConnection();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_no_internet_connection, container, false);
    }

}
