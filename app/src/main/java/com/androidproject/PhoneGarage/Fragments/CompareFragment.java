package com.androidproject.PhoneGarage.Fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.PhoneGarage.HelperAdapter.CompareAdapter;
import com.androidproject.PhoneGarage.JavaBeans.MainActivity;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * This class will compare phone devices
 * It includes ViewPager for small screen OR
 * displays phones next to each other on wide screens such as tablets
 * A simple {@link Fragment} subclass.
 * @author Ali Dali
 */
public class CompareFragment extends Fragment {

    // Response to adding to compare list
    public static final int PHONE_EXIST = -1;
    public static final int LIST_FULL = 0;
    public static final int PHONE_ADDED = 1;

    // Set Max number of phones in compare to 3
    private static final int MAX_PHONES = 3;

    // Key for shared preferences
    private static final String PHONES = "comp_phones";

    private static SharedPreferences sharedPreferences;
    private static Gson gson;

    // Need to access position in view pager in order to delete phone
    private static int currentPosition = 0;

    // List of phones to compare
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
    }

    /**
     * It will initialize all variables in order to add to comparing list
     * This is needed to add phones from external classes
     * @param context
     * @author Ali Dali
     */
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

        // Check orientation of the device when layout created
        int currentOrientation = getResources().getConfiguration().orientation;
        tablet = (currentOrientation == Configuration.ORIENTATION_LANDSCAPE);
//        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
//            tablet = true;
//        } else {
//            tablet = false;
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        // Check orientation of the device when changed
        tablet = (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE);
//        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            tablet = true;
//        } else {
//            tablet = false;
//        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compare, container, false);
        loadData();

        // Check if it's not a tablet OR in landscape
        if (!tablet || view.findViewById(R.id.fragment_phone_first) == null) {
            compareText = view.findViewById(R.id.compareText);
            compareText.setVisibility(View.GONE);

            fab = view.findViewById(R.id.fabBtn);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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


            adapter = new CompareAdapter(getChildFragmentManager(), phones);
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

            // Populate Tablet layout
            int[] layouts = {R.id.fragment_phone_first, R.id.fragment_phone_second, R.id.fragment_phone_third};
            final FloatingActionButton[] floatingActionButtons = {view.findViewById(R.id.fabBtn), view.findViewById(R.id.fabBtn2), view.findViewById(R.id.fabBtn3)};

            for (int i = 0; i < phones.size(); i++) {
                final int index = i;
                getChildFragmentManager().beginTransaction().replace(layouts[i], DetailsFragment.newInstance(phones.get(i)), "Phone" + i).commit();
                floatingActionButtons[i].setVisibility(View.VISIBLE);
                floatingActionButtons[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        removePhone(index);
                        getChildFragmentManager().beginTransaction().remove(getChildFragmentManager().findFragmentByTag("Phone" + index)).commit();
                        floatingActionButtons[index].setVisibility(View.GONE);
                        Toast.makeText(getContext(), getString(R.string.comp_removed), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        return view;
    }

    /**
     *  Check if index is greater than the list size then reduce it by 1 and call the function again!
     *  OR remove the phone from the list and save it in preference
     * @param index of the phone
     * @author Ali Dali
     */
    private void removePhone(int index) {
        if (index > phones.size() - 1) {
            removePhone(--index);
        } else {
            phones.remove(index);
            saveData();
        }
    }


    /**
     * Check if phone already exist or the list is full , otherwise add the phone to list and save it in preference
     * @param phone object
     * @return int that determines if phone (exist, added or list is full)
     */
    public int addPhoneToCompare(Phone phone) {
        loadData();

        if (MainActivity.comparePhones(phones, phone)) return PHONE_EXIST;

        if (phones.size() >= MAX_PHONES) return LIST_FULL;

        phones.add(phone);
        saveData();

        return PHONE_ADDED;
    }


    /**
     * Save list in shared preference
     * @author Ali Dali
     */
    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String jsonArray = gson.toJson(phones);
        editor.putString(PHONES, jsonArray);
        editor.commit();
    }

    /**
     * Load phones from shared preference
     * @author Ali Dali
     */
    private void loadData() {
        if (sharedPreferences != null) {
            String jsonArray = sharedPreferences.getString(PHONES, gson.toJson(new ArrayList<>())); // Default is an empty ArrayList<>()
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();
            phones = gson.fromJson(jsonArray, phoneType);
        }
    }


}
