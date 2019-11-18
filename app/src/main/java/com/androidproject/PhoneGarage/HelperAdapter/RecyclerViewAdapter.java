package com.androidproject.PhoneGarage.HelperAdapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.PhoneGarage.Fragments.FavouritesFragment;
import com.androidproject.PhoneGarage.JavaBeans.ItemClickListener;
import com.androidproject.PhoneGarage.JavaBeans.MyHolder;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<MyHolder> {

    Context context;
    ArrayList<Phone> phones;

    ViewGroup parent;

    public RecyclerViewAdapter(Context context, ArrayList<Phone> phones) {
        this.context = context;
        this.phones = phones;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        this.parent = parent;

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.mImageView.setImageResource(R.drawable.iphone);
        holder.mTitle.setText(phones.get(position).getBrand());
        holder.mDescription.setText(phones.get(position).getDeviceName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {

                Log.e("FAV", phones.equals(FavouritesFragment.phones) + "");

                Log.e("PHONE", phones.get(position).getDetailsFormatted());

                Bundle args = new Bundle();

                args.putSerializable("phone", phones.get(position));

                Log.e("PHONE", phones.get(position).getDetailsFormatted());


                // Check if you are in Home or Favourite Fragment
                if (phones.equals(FavouritesFragment.phones))
                    Navigation.findNavController(view).navigate(R.id.action_nav_favourites_to_detailsFragment, args);
                else
                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_detailsFragment, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }
}
