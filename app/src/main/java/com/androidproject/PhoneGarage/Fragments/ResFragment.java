package com.androidproject.PhoneGarage.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.PhoneGarage.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResFragment extends Fragment {


    public ResFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_res, container, false);

        return view;
    }

}
