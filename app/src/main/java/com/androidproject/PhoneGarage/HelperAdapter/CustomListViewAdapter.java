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


public class CustomListViewAdapter extends ArrayAdapter {

    public CustomListViewAdapter(@NonNull Context context, ArrayList<DetailsList> item) {
        super(context, 0, item);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DetailsList item = (DetailsList) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.details_list, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        name.setText(item.getName());

        TextView value = convertView.findViewById(R.id.value);
        value.setText(item.getValue());


        return convertView;

    }


}
