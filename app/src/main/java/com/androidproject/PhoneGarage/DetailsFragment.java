package com.androidproject.PhoneGarage;


import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.security.Permission;
import java.security.Permissions;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView title;
    TextView description;
    ImageView image;

    private Phone phone;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phone = (Phone) getArguments().getSerializable("phone");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);


        String mTitle = phone.getBrand();
        String mDescription = phone.getDetailsFormatted();

        image.setImageResource(R.drawable.iphone);
        title.setText(mTitle);
        description.setText(mDescription);

        Picasso.get().load("https://gdarwish.scweb.ca/PHP/phoneGarage/iphone7/apple-iPhone-7-blk-back.png").placeholder(R.drawable.iphone).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Log.e("PICASSO", "SUCCESS");
            }

            @Override
            public void onError(Exception e) {
                Log.e("PICASSO", e.getMessage() + " FAILED");

                e.getStackTrace();
            }
        });

        return view;
    }


    public static DetailsFragment newInstance(Phone phone) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("phone", phone);
        fragment.setArguments(args);
        return fragment;
    }

}