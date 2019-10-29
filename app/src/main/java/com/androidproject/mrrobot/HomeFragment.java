package com.androidproject.mrrobot;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    ArrayList<Phone> phones;
    ArrayList<Phone> searchedPhones;

    EditText searchText;
    TextView textView;
    String results = "";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchText = view.findViewById(R.id.searchText);
        textView = view.findViewById(R.id.textPhone);


        // Initiate local ArrayList of phones
        phones = Data.getInstance(getContext()).getPhonesList();
        displayPhones(phones);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                displayPhones(searchPhones(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }

    private ArrayList<Phone> searchPhones(String searchInput) {
        searchedPhones = new ArrayList<>();
        searchInput = searchInput.toUpperCase();

        for(Phone phone: phones) {
            String device = phone.getDeviceName().toUpperCase();
            if(device.contains(searchInput)) {
                searchedPhones.add(phone);
            }
        }
        return searchedPhones;
    }

    private void displayPhones(ArrayList<Phone> phones) {
        results = "";
        // Loop through  all phones and display them (or do whatever)
        for (Phone phone:phones
        ) {
            results += phone.getDetailsFormatted() + "\n";
        }

        textView.setText(results);
    }

}
