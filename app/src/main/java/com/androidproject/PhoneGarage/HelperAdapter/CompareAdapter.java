package com.androidproject.PhoneGarage.HelperAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.androidproject.PhoneGarage.Fragments.DetailsFragment;
import com.androidproject.PhoneGarage.JavaBeans.Phone;

import java.util.ArrayList;

/**
 *  Custom Adapter for ViewPager in Compare Layout
 * @author Ali Dali
 */
public class CompareAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Phone> phones;

    public CompareAdapter(FragmentManager fm, ArrayList<Phone> phones) {
        super(fm);
        this.phones = phones;
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