package com.androidproject.PhoneGarage.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.androidproject.PhoneGarage.JavaBeans.MainActivity;
import com.androidproject.PhoneGarage.JavaBeans.ModeSharePref;
import com.androidproject.PhoneGarage.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    ModeSharePref modeSharePref;
    public static Switch theme;

    public SettingsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        modeSharePref = new ModeSharePref(getContext());

        if (modeSharePref.loadNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        theme = view.findViewById(R.id.theme);
        theme.setChecked(modeSharePref.onOff());
        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    modeSharePref.setNightMode(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                    getActivity().finish();

                } else {
                    modeSharePref.setNightMode(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                    getActivity().finish();
                }
            }
        });

        return view;
    }

}