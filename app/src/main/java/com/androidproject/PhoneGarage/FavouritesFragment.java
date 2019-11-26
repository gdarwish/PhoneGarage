package com.androidproject.PhoneGarage;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    public static final int PHONE_EXIST = -1;
    public static final int PHONE_ADDED = 1;

    private static final String PHONES = "phones";

    RecyclerView mRecyclerView;
    Adapter mAdapter;

    TextView favouriteText;

    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    private static ArrayList<Phone> phones;

    private static FavouritesFragment instance;

    public static FavouritesFragment getInstance(Context context) {
        if (instance == null)
            instance = new FavouritesFragment(context);
        return instance;
    }

    private FavouritesFragment(Context context) {
        initialize(context);
    }

    private void initialize(Context context) {
        if (phones == null)
            phones = new ArrayList<>();
        if (gson == null)
            gson = new Gson();
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public FavouritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        favouriteText = view.findViewById(R.id.favouriteText);
        favouriteText.setVisibility(View.GONE);

        if (phones.isEmpty()) {
            favouriteText.setVisibility(View.VISIBLE);
        }


        mRecyclerView = view.findViewById(R.id.recyclerview2);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new Adapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    public int addPhoneToCompare(Phone phone) {
        loadData();

        if (MainActivity.comparePhones(phones, phone)) return PHONE_EXIST;

        phones.add(phone);
        saveData();

        return PHONE_ADDED;
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String jsonArray = gson.toJson(phones);

        editor.putString(PHONES, jsonArray);
        editor.commit();
    }

    private void loadData() {
        if (sharedPreferences != null) {
            String jsonArray = sharedPreferences.getString(PHONES, gson.toJson(new ArrayList<>())); // Default is an empty ArrayList<>()
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            phones = gson.fromJson(jsonArray, phoneType);
        }
    }

}
