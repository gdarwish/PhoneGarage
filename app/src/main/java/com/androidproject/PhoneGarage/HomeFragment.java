package com.androidproject.PhoneGarage;


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
import android.widget.TextView;

import java.util.ArrayList;


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

        mAdapter = new Adapter(getContext(), getList());

        // mAdapter = new Adapter(getContext(), getList());
        mAdapter = new Adapter(getContext(), phones);

        mRecyclerView.setAdapter(mAdapter);

        searchText = view.findViewById(R.id.searchText);
        textView = view.findViewById(R.id.textPhone);


        // Initiate local ArrayList of phones

        // DO NOT DISPLAY PHONES WHEN APP LAUNCHED
//        displayPhones(phones);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(""))
                    displayPhones(searchPhones(s.toString()));
                else
                    textView.setText("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private ArrayList<Phone> getList() {

        ArrayList<Phone> models = new ArrayList<>();
//
//        Model m = new Model();
//        m.setImage(R.drawable.ic_ic_about);
//        m.setTitle("Hello");
//        m.setDescription("World");
//        phones.add(m);
//
//        m = new Model();
//        m.setImage(R.drawable.ic_ic_about);
//        m.setTitle("Ghaith");
//        m.setDescription("World");
//        phones.add(m);
//
//        m = new Model();
//        m.setImage(R.drawable.ic_ic_about);
//        m.setTitle("Ali");
//        m.setDescription("World");
//        phones.add(m);
//
//        m = new Model();
//        m.setImage(R.drawable.ic_ic_about);
//        m.setTitle("Maher");
//        m.setDescription("World");
//        phones.add(m);

//        for (Phone phone : phones
//        ) {
//            Phone m = new Phone();
//            m.setImage(R.drawable.ic_ic_about);
//            m.setTitle(phone.getBrand());
//            m.setDescription(phone.getDeviceName());
//            models.add(m);
//        }

        return models;
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

        mAdapter = new Adapter(getContext(), getList());
        mRecyclerView.setAdapter(mAdapter);

        textView.setText(results);
    }

}
