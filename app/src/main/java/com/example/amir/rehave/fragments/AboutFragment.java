package com.example.amir.rehave.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;
import com.example.amir.rehave.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentAboutBinding binding=DataBindingUtil.inflate(
                inflater, R.layout.fragment_about, container, false);

        return binding.getRoot();
    }

}
