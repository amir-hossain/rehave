package com.example.amir.rehave.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;

public class AddictionInformationFragment extends Fragment {

    public static AddictionInformationFragment newInstance() {

        AddictionInformationFragment fragment = new AddictionInformationFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_addiction_information, container, false);
    }
}