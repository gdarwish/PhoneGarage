package com.androidproject.PhoneGarage.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.JavaBeans.MainActivity;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
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

    public static ArrayList<Phone> comparePhoneList;

    public static final int PHONE_EXIST = -1;
    public static final int LIST_FULL = 0;
    public static final int PHONE_ADDED = 1;

    private static final int MAX_PHONES = 3;
    private static final String PHONES = "phones";

    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    private static int currentPosition = 0;

    private static ArrayList<Phone> phones;


    private CompareAdapter adapter;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private TextView compareText;

    private boolean tablet;

    private static CompareFragment instance;

    public static CompareFragment getInstance(Context context) {
        if (instance == null)
            instance = new CompareFragment(context);
        return instance;
    }

    private CompareFragment(Context context) {
        initialize(context);
    }

    // Need a public constructor
    public CompareFragment() {
//        initialize(getContext());
    }

    private void initialize(Context context) {
        if (phones == null)
            phones = new ArrayList<>();
        if (gson == null)
            gson = new Gson();
        if (sharedPreferences == null)
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initialize(getContext());
        loadData();

        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.e("orientation", "PORTRAIT");
            tablet = false;
        } else {
            Log.e("orientation", "LANDSCAPE");
            tablet = true;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("orientation", "LANDSCAPE");
            tablet = true;
        } else {
            Log.e("orientation", "PORTRAIT");
            tablet = false;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);

        if (!tablet || view.findViewById(R.id.fragment_phone_first) == null) {
            compareText = view.findViewById(R.id.compareText);
            compareText.setVisibility(View.GONE);


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
                    Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
                }
            });

            if (phones.isEmpty()) {
                fab.setVisibility(View.GONE);
                compareText.setVisibility(View.VISIBLE);
            }


            adapter = new CompareAdapter(getChildFragmentManager());
            viewPager = view.findViewById(R.id.compareViewPager);
            viewPager.setAdapter(adapter);

            // Get the current position of the ViewPager
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
        } else {


            if (phones.size() >= 1) {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_phone_first, DetailsFragment.newInstance(phones.get(0)), "0").commit();
                final FloatingActionButton fab1 = view.findViewById(R.id.fabBtn);
                fab1.setVisibility(View.VISIBLE);
                fab1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phones.remove(0);
                        saveData();
                        getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentByTag("0")).commit();
                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
                        fab1.setVisibility(View.GONE);
                    }
                });
            }

            if (phones.size() >= 2) {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_phone_second, DetailsFragment.newInstance(phones.get(1)), "1").commit();
                final FloatingActionButton fabBtn = view.findViewById(R.id.fabBtn2);
                fabBtn.setVisibility(View.VISIBLE);
                fabBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phones.remove(phones.size() - 2 < 0 ? 0 : phones.size() - 2);
                        saveData();
                        getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentByTag("1")).commit();
                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
                        fabBtn.setVisibility(View.GONE);
                    }
                });
            }

            if (phones.size() >= 3) {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_phone_third, DetailsFragment.newInstance(phones.get(2)), "2").commit();

                final FloatingActionButton fabBtn = view.findViewById(R.id.fabBtn3);
                fabBtn.setVisibility(View.VISIBLE);
                fabBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        phones.remove(phones.size() - 1 < 0 ? 0 : phones.size() - 1);
                        saveData();
                        getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentByTag("2")).commit();
                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
                        fabBtn.setVisibility(View.GONE);
                    }
                });
            }



//            final FloatingActionButton[] floatingActionButtons = {view.findViewById(R.id.fabBtn), view.findViewById(R.id.fabBtn2), view.findViewById(R.id.fabBtn3)};

//            for(int i = 0; i < phones.size(); i++) {
//                floatingActionButtons[i].setVisibility(View.VISIBLE);
//                floatingActionButtons[i].setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        phones.remove(currentPosition);
//                        saveData();
//                        getFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentByTag("phone")).commit();
//                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
//                        floatingActionButtons[i].setVisibility(View.GONE);
//                    }
//                });
//            }

//            for(final FloatingActionButton fab : floatingActionButtons) {
//                fab.setVisibility(View.VISIBLE);
//                fab.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        saveData();
//                        getFragmentManager().beginTransaction().repla(getChildFragmentManager().findFragmentByTag("phone")).commit();
//                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
//                        fab.setVisibility(View.GONE);
//                    }
//                });
//
//            }



        }

        return view;
    }


    public int addPhoneToCompare(Phone phone) {
        loadData();

        Log.e("CONTAIN", phones.contains(phone) + " contains");
        Log.e("CONTAIN", MainActivity.comparePhones(phones, phone) + " compares");
        Log.e("CONTAIN", phone + "");

        if (MainActivity.comparePhones(phones, phone)) return PHONE_EXIST;

        if (phones.size() >= MAX_PHONES) return LIST_FULL;

        phones.add(phone);
        saveData();

        return PHONE_ADDED;
    }

    public void removePhoneFromCompare() {

    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String jsonArray = gson.toJson(phones);

        Log.e("PHONES", jsonArray + "save");

        editor.putString(PHONES, jsonArray);
        editor.commit();

    }

    private void loadData() {
        if (sharedPreferences != null) {
            String jsonArray = sharedPreferences.getString(PHONES, gson.toJson(new ArrayList<>())); // Default is an empty ArrayList<>()
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            phones = gson.fromJson(jsonArray, phoneType);

            Log.e("PHONES", jsonArray + "load");
            Log.e("PHONES", phones.toString() + "phones array");
        }
    }


    private class CompareAdapter extends FragmentStatePagerAdapter {

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
