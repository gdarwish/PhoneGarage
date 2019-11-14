package com.androidproject.PhoneGarage;


import android.content.Context;
import android.content.SharedPreferences;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CompareFragment extends Fragment {

    private static final int MAX_PHONES = 4;
    private static final String PHONES = "phones";

    private  SharedPreferences sharedPreferences;
    private static Gson gson;

    private static int currentPosition = 0;

    private static ArrayList<Phone> phones = new ArrayList<>();


    private CompareAdapter adapter;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private TextView compareText;

    public CompareFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();

        loadData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        compareText = view.findViewById(R.id.compareText);
        compareText.setVisibility(View.GONE);

        // TEST

//        phones.clear();


        fab = view.findViewById(R.id.fabBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("POSITION", currentPosition + "");
                phones.remove(currentPosition);
                adapter.notifyDataSetChanged();
                if (phones.isEmpty()) {
                    fab.setVisibility(View.GONE);
                    compareText.setVisibility(View.VISIBLE);
                }
                saveData();
                Toast.makeText(getContext(), "Phone is deleted", Toast.LENGTH_SHORT).show();
            }
        });

        if (phones.isEmpty()) {
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

    private void loadData() {
        if (sharedPreferences != null) {
            String jsonArray = sharedPreferences.getString(PHONES, null);
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            phones = gson.fromJson(jsonArray, phoneType);
            Log.e("PHONES", jsonArray + "load");

            Log.e("PHONES", phones.toString() + "phones array");
        }
    }

    private void saveData() {
        sharedPreferences = getActivity().getSharedPreferences(PHONES, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        String jsonArray = null;

        if (!phones.isEmpty())
            jsonArray = gson.toJson(phones);

        Log.e("PHONES", jsonArray + "save");

        editor.putString(PHONES, jsonArray);
        editor.commit();

    }

    public static boolean addPhoneToCompare(Phone phone) {
        if (phones.contains(phone)) return false;

        phones.add(phone);

        return true;
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
            if (phones.contains(phone)) {
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
