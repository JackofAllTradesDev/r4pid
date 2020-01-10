package com.rivera.rapid.dashboard;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rivera.rapid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PorceleinFragment extends Fragment {


    public PorceleinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_porcelein, container, false);
    }

}
