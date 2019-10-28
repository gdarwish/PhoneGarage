package com.androidproject.mrrobot;


import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.androidproject.mrrobot.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView mRecyclerView;
    Adapter mAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //FragmentActivity c = getActivity();

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

       // mAdapter = new Adapter(getContext(), getList());
        mAdapter = new Adapter(getContext(), getList());
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.setHasFixedSize(true);

        return view;
    }

    private ArrayList<Model> getList() {

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();
        m.setImage(R.drawable.ic_ic_about);
        m.setTitle("Hello");
        m.setDescription("World");
        models.add(m);

        m = new Model();
        m.setImage(R.drawable.ic_ic_about);
        m.setTitle("Hello");
        m.setDescription("World");
        models.add(m);

        m = new Model();
        m.setImage(R.drawable.ic_ic_about);
        m.setTitle("Hello");
        m.setDescription("World");
        models.add(m);

        m = new Model();
        m.setImage(R.drawable.ic_ic_about);
        m.setTitle("Hello");
        m.setDescription("World");
        models.add(m);

        return models;
    }

}
