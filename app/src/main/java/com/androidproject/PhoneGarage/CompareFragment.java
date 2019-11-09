package com.androidproject.PhoneGarage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {

    public static ArrayList<Phone> comparePhoneList;

    public CompareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);


        return view;
    }

}
