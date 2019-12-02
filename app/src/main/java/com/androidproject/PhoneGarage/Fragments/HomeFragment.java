package com.androidproject.PhoneGarage.Fragments;


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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.HelperAdapter.RecyclerViewAdapter;
import com.androidproject.PhoneGarage.JavaBeans.Data;
import com.androidproject.PhoneGarage.JavaBeans.ButtonClickListener;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.androidproject.PhoneGarage.JavaBeans.SwipeHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Gaith Darwish
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Log.e("PHONE", "phone " + phones.get(0).getDetailsFormatted());
        Log.v("PHONE", "phone " + phones.get(0).getDetailsFormatted());
        Log.v("PHONE", "phone ");

        /**
         * @author Ghaith Darwish
         * Createing an instance of the SwipeHelper and set it on the RecyclerView
         */
        SwipeHelper swipeHelper = new SwipeHelper(getContext(), mRecyclerView, 200) {
            @Override
            public void insantiateMyButton(RecyclerView.ViewHolder viewHolder, List<SwipeHelper.MyButton> buffer) {
                // Create the Favorite button and set its color text and size when swiped and put event listener on its
                buffer.add(new MyButton(getContext(), "heart", 60, 0, Color.parseColor("#FFBE3233"), new ButtonClickListener() {
                    @Override
                    // Adding the functionality on the button and display the result on a Toast according to the condition
                    public void onClick(int position) {
                        int result = FavouritesFragment.getInstance(getContext()).addPhoneToCompare(phones.get(position));
                        String message = "";
                        switch (result) {
                            // if the phone is already exist
                            case CompareFragment.PHONE_EXIST:
                                message = getString(R.string.fav_exist);
                                break;
                            // if the phone got added
                            case CompareFragment.PHONE_ADDED:
                                message = getString(R.string.fav_added);
                                break;
                        }
                        //  show the toast
                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }));
                // Create the Compare button and set its color text and size when swiped and put event listener on its
                buffer.add(new MyButton(getContext(), "retweet", 70, 0, Color.parseColor("#FF4633F7"), new ButtonClickListener() {

                    @Override
                    // Adding the functionality on the button and display the result on a Toast according to the condition
                    public void onClick(int position) {
                        int result = CompareFragment.getInstance(getContext()).addPhoneToCompare(phones.get(position));
                        String message = "";
                        switch (result) {
                            // if the Compare list is full
                            case CompareFragment.LIST_FULL:
                                message = getString(R.string.comp_full);
                                break;
                            // if the phone is already exist
                            case CompareFragment.PHONE_EXIST:
                                message = getString(R.string.comp_exist);
                                break;
                            // if the phone got added
                            case CompareFragment.PHONE_ADDED:
                                message = getString(R.string.comp_added);
                        }

                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

                    }
                }));
            }
        };
        // SwipeHelper ends here

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    // Update recycler view
                    mAdapter.updatePhonesList(searchPhones(s.toString()));
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
            if (deviceName.contains(searchInput)) {
                searchedPhones.add(phone);
            }
        }
        return searchedPhones;
    }
}
