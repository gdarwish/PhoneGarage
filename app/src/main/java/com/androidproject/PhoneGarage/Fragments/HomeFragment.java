package com.androidproject.PhoneGarage.Fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
//<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;
//=======
//>>>>>>> Staging

import com.androidproject.PhoneGarage.HelperAdapter.RecyclerViewAdapter;
import com.androidproject.PhoneGarage.HelperAdapter.SliderAdapterExample;
import com.androidproject.PhoneGarage.JavaBeans.Data;
import com.androidproject.PhoneGarage.JavaBeans.ButtonClickListener;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.androidproject.PhoneGarage.JavaBeans.SwipeHelper;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerViewAdapter mAdapter;


    ArrayList<Phone> phones;
    ArrayList<Phone> searchedPhones;
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

        mAdapter = new RecyclerViewAdapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        searchText = view.findViewById(R.id.searchText);

        SwipeHelper swipeHelper = new SwipeHelper(getContext(), mRecyclerView, 200) {
            @Override
            public void insantiateMyButton(RecyclerView.ViewHolder viewHolder, List<SwipeHelper.MyButton> buffer) {

                buffer.add(new MyButton(getContext(), "heart", 60, 0, Color.parseColor("#FFBE3233"), new ButtonClickListener() {

                    @Override
                    public void onClick(int pos) {
                        Toast.makeText(getContext(), "Added to Favorite!", Toast.LENGTH_SHORT).show();


                    }
                }));

                buffer.add(new MyButton(getContext(), "arrows-alt-h", 70, 0, Color.parseColor("#FF4633F7"), new ButtonClickListener() {

                    @Override
                    public void onClick(int pos) {
                        int result = CompareFragment.getInstance(getContext()).addPhoneToCompare(phones.get(pos));
                        String message = "";
                        switch (result) {
                            case CompareFragment.LIST_FULL:
                                message = "Compare is full";
                                break;
                            case CompareFragment.PHONE_EXIST:
                                message = "Phone already exist";
                                break;
                            case CompareFragment.PHONE_ADDED:
                                message = "Added to Compare";
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
