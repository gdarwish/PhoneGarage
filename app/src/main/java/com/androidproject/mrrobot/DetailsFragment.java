package com.androidproject.mrrobot;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView title;
    TextView description;
    ImageView image;


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        Phone phone =  (Phone)getArguments().getSerializable("phone");

        String mTitle = phone.getBrand();
        String mDescription = phone.getDeviceName();
//        int  mImg = getArguments().getInt("img");

//        String mTitle = getArguments().getString("title");
//        String mDescription = getArguments().getString("desc");
//        int  mImg = getArguments().getInt("img");

        image.setImageResource(R.drawable.ic_ic_favoriteemp);
        title.setText(mTitle);
        description.setText(mDescription);

        return view;
    }

}