package com.androidproject.PhoneGarage.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.androidproject.PhoneGarage.JavaBeans.Consts;
import com.androidproject.PhoneGarage.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopersFragment extends Fragment {

    ImageView developerImage;
    Button webButton;
    Button emailButton;
    Button facButton;
    Button callButton;
    Button locaButton;

    int image;
    String website;
    String[] email;
    String facebook;
    String phone;
    String location;



    public DevelopersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            image = getArguments().getInt(Consts.image, 0);
            website = getArguments().getString(Consts.website, "");
            email = getArguments().getStringArray(Consts.email);
            facebook = getArguments().getString(Consts.facebook, "");
            phone = getArguments().getString(Consts.phone, "");
            location = getArguments().getString(Consts.location, "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_developers, container, false);

        developerImage = view.findViewById(R.id.developer_img);
        developerImage.setImageResource(image);

        webButton = view.findViewById(R.id.webButton);
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = website;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        emailButton = view.findViewById(R.id.emailButton);
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Information");
                intent.putExtra(Intent.EXTRA_TEXT, "I would like to get more info about.....");
                startActivity(intent);
            }
        });


        facButton = view.findViewById(R.id.facButton);
        facButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(facebook));
                startActivity(i);
            }
        });


        callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        locaButton = view.findViewById(R.id.locaButton);
        locaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri locationUri = Uri.parse(location);
                Intent intent = new Intent(Intent.ACTION_VIEW, locationUri);
                startActivity(intent);
            }
        });


        return view;
    }

}
