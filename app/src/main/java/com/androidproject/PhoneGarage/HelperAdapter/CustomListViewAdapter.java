package com.androidproject.PhoneGarage.HelperAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidproject.PhoneGarage.JavaBeans.DetailsList;
import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author gaithdarwish
 */
public class CustomListViewAdapter extends ArrayAdapter {

    /**
     * @param context
     * @param item
     */
    public CustomListViewAdapter(@NonNull Context context, ArrayList<DetailsList> item) {
        super(context, 0, item);
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DetailsList item = (DetailsList) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details_list, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(item.getTitle());

        TextView value = convertView.findViewById(R.id.value);
        value.setText(item.getValue());


        return convertView;

    }


}
