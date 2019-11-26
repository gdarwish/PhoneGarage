package com.androidproject.PhoneGarage.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.JavaBeans.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import com.androidproject.PhoneGarage.HelperAdapter.RecyclerViewAdapter;
import com.androidproject.PhoneGarage.JavaBeans.ButtonClickListener;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.JavaBeans.SwipeHelper;
import com.androidproject.PhoneGarage.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {

    public static final int PHONE_EXIST = -1;
    public static final int PHONE_ADDED = 1;

    private static final String PHONES = "phones";

    RecyclerView mRecyclerView;

    RecyclerViewAdapter mAdapter;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(getContext());
        loadData();
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

        mAdapter = new RecyclerViewAdapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        // swipe view
        SwipeHelper swipeHelper = new SwipeHelper(getActivity(), mRecyclerView, 200) {

            @Override
            public void insantiateMyButton(RecyclerView.ViewHolder viewHolder, List<MyButton> buffer) {

                buffer.add(new MyButton(getContext(), "trash-alt", 40, 0, Color.parseColor("#FFBE3233"), new ButtonClickListener() {

                    @Override
                    public void onClick(int position) {

                        phones.remove(phones.get(position));

                        mAdapter.updatePhonesList(phones);

                        Toast.makeText(getContext(), "Deleted from favourite", Toast.LENGTH_SHORT).show();
                    }
                }));
            }
        };

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
