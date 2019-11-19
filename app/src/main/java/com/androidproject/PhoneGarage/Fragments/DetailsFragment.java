package com.androidproject.PhoneGarage.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


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


        Picasso.get().load(phone.getImageUrl()).into(image, new Callback() {
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


        title.setText(phone.getBrand());
        description.setText(phone.getDetailsFormatted());


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