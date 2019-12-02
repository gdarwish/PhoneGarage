package com.androidproject.PhoneGarage.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.androidproject.PhoneGarage.HelperAdapter.CustomListViewAdapter;
import com.androidproject.PhoneGarage.JavaBeans.DetailsList;
import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResourcesFragment extends Fragment {

    ListView res_list;
    String url = "";


    public ResourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_resources, container, false);

        res_list = view.findViewById(R.id.res_list);
        final ArrayList<DetailsList> list = new ArrayList<>();
        list.add(new DetailsList(getString(R.string.resources_icons), "Dave Gandy", "https://www.flaticon.com/authors/dave-gandy"));
        list.add(new DetailsList(getString(R.string.resources_logo), "Free Online Logo Maker", "https://www.designevo.com/logo-maker/"));
        list.add(new DetailsList(getString(R.string.resources_images), "Rogers", "https://www.rogers.com/consumer/ho"));
        list.add(new DetailsList(getString(R.string.resources_others), "Font Awesome", "https://fontawesome.com/"));
        list.add(new DetailsList(getString(R.string.resources_color_design), "Ghaith Darwish", "https://gdarwish.scweb.ca/GDWeb/"));

         CustomListViewAdapter adapte = new CustomListViewAdapter(getContext(), list);
        res_list.setAdapter(adapte);

        res_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                web(list.get(i).getUrl());
            }
        });

        return view;
    }

    public void web(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}
