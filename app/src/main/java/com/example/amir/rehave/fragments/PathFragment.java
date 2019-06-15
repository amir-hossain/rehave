package com.example.amir.rehave.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amir.rehave.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PathFragment extends Fragment {
    private Fragment fragmentToRun;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.path_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.addiction_btn)
    public void addictionBtnClick(){
        fragmentToRun = AddictionInformationFragment.newInstance();
        runFragment();
    }

    @OnClick(R.id.protection_btn)
    public void protectionBtnClick(){
        fragmentToRun = RelapseProtectionFragment.newInstance();
        runFragment();
    }

    private void runFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_layout, fragmentToRun)
                .addToBackStack(null)
                .commit();
    }
}
