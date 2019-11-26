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
//<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
//=======
//>>>>>>> Staging

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
    TextView textView;

    EditText searchText;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        phones = Data.getInstance(getContext()).getPhonesList();

        mRecyclerView = view.findViewById(R.id.recyclerview2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new Adapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        searchText = view.findViewById(R.id.searchText);
//<<<<<<< HEAD
//        textView = view.findViewById(R.id.textPhone);


        // start new code here
        SwipeHelper swipeHelper = new SwipeHelper(getContext(), mRecyclerView, 200) {
            @Override
            public void insantiateMyButton(RecyclerView.ViewHolder viewHolder, List<SwipeHelper.MyButton> buffer) {

                buffer.add(new MyButton(getContext(), "Favorites", 40, 0, Color.parseColor("#FFBE3233"), new MyButtonClickListener() {

                    @Override
                    public void onClick(int pos) {
                        int result = FavouritesFragment.getInstance(getContext()).addPhoneToCompare(phones.get(pos));
                        String message = "";
                        switch (result) {
                            case CompareFragment.PHONE_EXIST:
                                message = "Phone already exist in favourites.";
                                break;
                            case CompareFragment.PHONE_ADDED:
                                message = "Phone added to favourites.";
                                break;
                        }

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                    }
                }));

                buffer.add(new MyButton(getContext(), "Compare", 40, 0, Color.parseColor("#FF4633F7"), new MyButtonClickListener() {

                    @Override
                    public void onClick(int pos) {
                        int result = CompareFragment.getInstance(getContext()).addPhoneToCompare(phones.get(pos));
                        String message = "";
                        switch (result) {
                            case CompareFragment.LIST_FULL:
                                message = "Comparing Phones List is full.";
                                break;
                            case CompareFragment.PHONE_EXIST:
                                message = "Phone already exist in Comparing List.";
                                break;
                            case CompareFragment.PHONE_ADDED:
                                message = "Phone added to Comparing List.";
                                break;
                        }

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

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
                    // Update recycler view
                    mAdapter.setPhones(searchPhones(s.toString()));
                    mAdapter.notifyDataSetChanged();
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
            String deviceName = phone.getDeviceName().toUpperCase();
            String[] fullName = deviceName.split(" ");
            for (String name : fullName) {
                if (name.contains(searchInput)) {
                    searchedPhones.add(phone);
                }
            }
        }
        return searchedPhones;
    }

}
