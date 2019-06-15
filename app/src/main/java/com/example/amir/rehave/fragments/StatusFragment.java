package com.example.amir.rehave.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;
import com.example.amir.rehave.databinding.FragmentStatusBinding;

public class StatusFragment extends Fragment {
    public static StatusFragment newInstance() {
        StatusFragment fragment = new StatusFragment();

        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentStatusBinding binding= DataBindingUtil.inflate(
                inflater, R.layout.fragment_status, container, false);

        return binding.getRoot();
    }
}
