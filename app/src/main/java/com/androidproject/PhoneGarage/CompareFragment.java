package com.androidproject.PhoneGarage;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {

    public static final int MAX_PHONES = 4;

    private CompareAdapter adapter;
    private ViewPager viewPager;

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

        adapter = new CompareAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.compareViewPager);
        viewPager.setAdapter(adapter);

        return view;
    }

    class CompareAdapter extends FragmentPagerAdapter {

        public CompareAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DetailsFragment.newInstance(phones.get(position));
        }

        @Override
        public int getCount() {
            return MAX_PHONES;
        }
    }
}
