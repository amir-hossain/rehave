package com.example.amir.rehave.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;


public class RelapseProtectionFragment extends Fragment {

    public static RelapseProtectionFragment newInstance() {
        RelapseProtectionFragment fragment = new RelapseProtectionFragment();

        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_relapse_protection, container, false);
    }


}
