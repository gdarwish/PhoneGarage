package com.androidproject.PhoneGarage.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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

import java.util.Locale;


/**
 * @author gaithdarwish
 */
public class SettingsFragment extends Fragment {

    Switch theme;
    Switch layout;
    Switch language;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Save the status to sharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = sharedPreferences.edit();

        theme = view.findViewById(R.id.theme);
        // Set NightMode to false
        theme.setChecked(sharedPreferences.getBoolean("NightMode", false));
        theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // Save the status when the switch is checked
                editor.putBoolean("NightMode", b);
                editor.commit();
                // if NightMode is true change the app to NightMode, else set it to light mode
                AppCompatDelegate.setDefaultNightMode(b ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                // Restart the app
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                getActivity().finish();
            }
        });
        // Change app layout
        layout = view.findViewById(R.id.layout_customize);
        // set change app layout to false
        layout.setChecked(sharedPreferences.getBoolean("layout", false));
        layout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // save the stats when the switch is checked
                editor.putBoolean("layout", b);
                editor.commit();
                // Restart the app
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                getActivity().finish();
            }
        });

        // Change language
        language = view.findViewById(R.id.language_setting);
        // set language to english as default
        language.setChecked(sharedPreferences.getBoolean("language", false));
        language.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Locale locale;
                if(b) {
                    locale = new Locale("ar");
                } else {
                    locale = new Locale("en");
                }
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                config.locale = locale;
                getActivity().getBaseContext().getResources().updateConfiguration(config, getActivity().getBaseContext().getResources().getDisplayMetrics());


                // save the stats when the switch is checked
                editor.putBoolean("language", b);
                editor.commit();

                // Restart the app
                getActivity().startActivity(new Intent(getActivity().getApplicationContext(), MainActivity.class));
                getActivity().finish();
            }
        });




        return view;
    }
}