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
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {

    public static final int MAX_PHONES = 4;

    private CompareAdapter adapter;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private TextView compareText;

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

        compareText = view.findViewById(R.id.compareText);
        compareText.setVisibility(View.GONE);

        // TEST
        phones.clear();

        phones.add(Data.getInstance(getContext()).getPhonesList().get(0));
        phones.add(Data.getInstance(getContext()).getPhonesList().get(1));
        phones.add(Data.getInstance(getContext()).getPhonesList().get(2));
        phones.add(Data.getInstance(getContext()).getPhonesList().get(3));






        fab = view.findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POSITION", currentPosition + "");
                phones.remove(currentPosition);
                adapter.notifyDataSetChanged();
                if(phones.isEmpty()) {
                    fab.setVisibility(View.GONE);
                    compareText.setVisibility(View.VISIBLE);
                }
                Toast.makeText(getContext(), "Phone is deleted", Toast.LENGTH_SHORT).show();
            }
        });

        if(phones.isEmpty()) {
            fab.setVisibility(View.GONE);
            compareText.setVisibility(View.VISIBLE);
        }


        adapter = new CompareAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.compareViewPager);
        viewPager.setAdapter(adapter);

        // Get the current position of the viewpager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
                return phones.indexOf(phone);
            } else {
                return POSITION_NONE;
            }
        }

        @Override
        public int getCount() {
            return phones.size();
        }
    }
}
