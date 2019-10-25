package com.androidproject.mrrobot;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView textView = view.findViewById(R.id.textPhone);
        String text = "";


        // Get Device Name of phone with index 100
        String iPhone = Data.getInstance(getContext()).getPhonesList().get(100).getDeviceName();

        // Accessing phone with multiple simple steps
        Data data = Data.getInstance(getContext());
        ArrayList<Phone>  phonesList = data.getPhonesList();
        Phone firstPhone = phonesList.get(0);


        // Initiate local ArrayList of phones
        ArrayList<Phone> phones = Data.getInstance(getContext()).getPhonesList();

        // Loop through  all phones and display them (or do whatever)
        for (Phone phone:phones
             ) {
            text += phone.getDetailsFormatted() + "\n";
        }

        textView.setText(text);

        return view;
    }

}
