package com.androidproject.PhoneGarage.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidproject.PhoneGarage.JavaBeans.Consts;
import com.androidproject.PhoneGarage.R;


/**
 * @author Ghauth Darwish
 */
public class AboutFragment extends Fragment {

    Button ghaithButton;
    Button aliButton;
    Button resButton;

    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        final Bundle args = new Bundle();

        ghaithButton = view.findViewById(R.id.ghaithButton);
        ghaithButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                args.putInt(Consts.image, R.drawable.ghaithimg);
                args.putString(Consts.website, "https://gdarwish.scweb.ca/GDWeb/");
                args.putStringArray(Consts.email, new String[]{"gaithdarwish1@gmail.com"});
                args.putString(Consts.facebook, "https://www.facebook.com/alexandar.alexandar.98");
                args.putString(Consts.phone, "+34666777888");
                args.putString(Consts.location, "geo:0,0?q=42.2465,-83.0191 St.Clair College");
                args.putString(Consts.addToContact, "+34666777888");

                Navigation.findNavController(view).navigate(R.id.action_nav_about_to_developersFragment, args);
            }
        });

        aliButton = view.findViewById(R.id.aliButton);
        aliButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                args.putInt(Consts.image, R.drawable.aliimg);
                args.putString(Consts.website, "https://adali.scweb.ca/");
                args.putStringArray(Consts.email, new String[]{"ali.dali01@stclairconnect.ca"});
                args.putString(Consts.facebook, "https://www.facebook.com/");
                args.putString(Consts.phone, "+222");
                args.putString(Consts.location, "geo:0,0?q=42.2465,-83.0191 St.Clair College");
                args.putString(Consts.addToContact, "+222");

                Navigation.findNavController(view).navigate(R.id.action_nav_about_to_developersFragment, args);
            }
        });

        resButton = view.findViewById(R.id.resButton);
        resButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_about_to_resFragment);
            }
        });

        return view;
    }

}
