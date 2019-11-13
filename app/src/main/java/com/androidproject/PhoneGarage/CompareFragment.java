package com.androidproject.PhoneGarage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {

    public static final int MAX_PHONES = 4;

    private CompareAdapter adapter;
    private ViewPager viewPager;
    private Button remove;

    private static int currentPosition = 0;

    public static ArrayList<Phone> phones = new ArrayList<>();

    public CompareFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        phones = Data.getInstance(getContext()).getPhonesList();

        remove = view.findViewById(R.id.remove);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("position", currentPosition + "");
                phones.remove(currentPosition);
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new CompareAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.compareViewPager);
        viewPager.setAdapter(adapter);

        return view;
    }

    class CompareAdapter extends FragmentStatePagerAdapter {

        public CompareAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DetailsFragment.newInstance(phones.get(position));
        }

        @Override
        public int getItemPosition(Object phone) {
            if(phones.contains(phone)) {
                currentPosition = phones.indexOf(phone);
                return phones.indexOf(phone);
            } else {
                return POSITION_NONE;
            }

        }

        @Override
        public int getCount() {
            return MAX_PHONES;
        }
    }
}
