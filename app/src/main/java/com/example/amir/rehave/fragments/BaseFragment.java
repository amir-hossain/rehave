package com.example.amir.rehave.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.amir.rehave.R;

public class BaseFragment extends Fragment {
    public void startFragment(Fragment fragment){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}
