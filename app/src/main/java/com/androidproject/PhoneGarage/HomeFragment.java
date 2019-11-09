package com.androidproject.PhoneGarage;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    Adapter mAdapter;


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

        phones = Data.getInstance(getContext()).getPhonesList();

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new Adapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        searchText = view.findViewById(R.id.searchText);
        textView = view.findViewById(R.id.textPhone);


        // start new code here
        SwipeHelper swipeHelper = new SwipeHelper(getContext(), mRecyclerView, 200) {
            @Override
            public void insantiateMyButton(RecyclerView.ViewHolder viewHolder, List<SwipeHelper.MyButton> buffer) {

                buffer.add(new MyButton(getContext(), "Favorite", 40, R.drawable.ic_ic_favorite, Color.parseColor("#FFBE3233"), new MyButtonClickListener() {

                    @Override
                    public void onClick(int pos) {

                        Toast.makeText(getContext(), "Successfully added to favorite!", Toast.LENGTH_SHORT).show();
                        Log.d("MyApp", "I am here");

                    }
                }));

                buffer.add(new MyButton(getContext(), "Compare", 40, R.drawable.ic_ic_compare, Color.parseColor("#FF4633F7"), new MyButtonClickListener() {

                    @Override
                    public void onClick(int pos) {
                        Toast.makeText(getContext(), "successfully added to compare!", Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        };


        // Initiate local ArrayList of phones

        // DO NOT DISPLAY PHONES WHEN APP LAUNCHED
//        displayPhones(phones);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
//                    displayPhones(searchPhones(s.toString()));

                    mAdapter.setPhones(searchPhones(s.toString()));
                    mAdapter.notifyDataSetChanged();
                } else {
                    textView.setText("");
                }
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

        for (Phone phone : phones) {
            String device = phone.getDeviceName().toUpperCase();
            if (device.contains(searchInput)) {
                searchedPhones.add(phone);
            }
        }
        return searchedPhones;
    }

    private void displayPhones(ArrayList<Phone> phones) {
        results = "";
        // Loop through  all phones and display them (or do whatever)
        for (Phone phone : phones
        ) {
            results += phone.getDetailsFormatted() + "\n";
        }

        textView.setText(results);
    }

}
