package com.androidproject.PhoneGarage.Fragments;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.androidproject.PhoneGarage.HelperAdapter.CustomListViewAdapter;
import com.androidproject.PhoneGarage.HelperAdapter.SliderAdapterExample;
import com.androidproject.PhoneGarage.JavaBeans.DetailsList;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    TextView title;
    TextView description;
    ListView listView;

    private Phone phone;

    public DetailsFragment() {
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

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        title.setText(phone.getBrand());

        // ImageSlider starts here
        SliderView sliderView = view.findViewById(R.id.imageSlider);
        SliderAdapterExample adapter = new SliderAdapterExample(getContext(), phone.getImageUrl());
        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        // ends

        // Details listView
        listView = view.findViewById(R.id.listView);

        final ArrayList<DetailsList> list = new ArrayList<>();
        list.add(new DetailsList(getString(R.string.phone_brand), phone.getBrand()));
        list.add(new DetailsList(getString(R.string.phone_size), phone.getSize()));
        list.add(new DetailsList(getString(R.string.phone_resolution), phone.getResolution()));
        list.add(new DetailsList(getString(R.string.phone_dimensions), phone.getDimensions()));
        list.add(new DetailsList(getString(R.string.phone_weight), phone.getWeight()));
        list.add(new DetailsList(getString(R.string.phone_screen_type), phone.getScreenType()));
        list.add(new DetailsList(getString(R.string.phone_card_slot), phone.getCardSlot()));
        list.add(new DetailsList(getString(R.string.phone_wlan), phone.getWlan()));
        list.add(new DetailsList(getString(R.string.phone_bluetooth), phone.getBluetooth()));
        list.add(new DetailsList(getString(R.string.phone_gps), phone.getGps()));
        list.add(new DetailsList(getString(R.string.phone_battery), phone.getBatteryCapacity()));
        list.add(new DetailsList(getString(R.string.phone_colors), phone.getColors()));
        list.add(new DetailsList(getString(R.string.phone_sensors), phone.getSensors()));
        list.add(new DetailsList(getString(R.string.phone_cpu), phone.getCpu()));
        list.add(new DetailsList(getString(R.string.phone_internal_storage), phone.getInternal()));
        list.add(new DetailsList(getString(R.string.phone_os), phone.getOs()));
        list.add(new DetailsList(getString(R.string.phone_video), phone.getVideo()));
        list.add(new DetailsList(getString(R.string.phone_gpu), phone.getGpu()));
        list.add(new DetailsList(getString(R.string.phone_camera_features), phone.getCameraFeature()));
        list.add(new DetailsList(getString(R.string.phone_front_camera), phone.getFrontCamera()));
        list.add(new DetailsList(getString(R.string.phone_primary_camera), phone.getDualCamera()));
        list.add(new DetailsList(getString(R.string.phone_charging), phone.getCharging()));

        CustomListViewAdapter adapterList = new CustomListViewAdapter(getContext(), list);
        listView.setAdapter(adapterList);

        //end
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