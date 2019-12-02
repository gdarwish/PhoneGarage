package com.androidproject.PhoneGarage.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.androidproject.PhoneGarage.JavaBeans.MainActivity;
import com.androidproject.PhoneGarage.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    Switch theme;
    Switch layout;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
         editor = sharedPreferences.edit();
        theme = view.findViewById(R.id.theme);
        theme.setChecked(sharedPreferences.getBoolean("NightMode", false));
        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("NightMode", b);
                editor.commit();
                AppCompatDelegate.setDefaultNightMode( b ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        layout = view.findViewById(R.id.layout_customize);
        layout.setChecked(sharedPreferences.getBoolean("layout", false));
        layout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("layout", b);
                editor.commit();
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}