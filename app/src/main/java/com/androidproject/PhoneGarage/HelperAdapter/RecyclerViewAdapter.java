package com.androidproject.PhoneGarage.HelperAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.PhoneGarage.JavaBeans.ItemClickListener;
import com.androidproject.PhoneGarage.JavaBeans.MyHolder;
import com.androidproject.PhoneGarage.JavaBeans.Phone;
import com.androidproject.PhoneGarage.R;
import com.squareup.picasso.Picasso;

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

        Phone phone = phones.get(position);


        Picasso.get().load(phone.getImageUrl()).placeholder(R.drawable.iphone).into(holder.mImageView);

//        holder.mImageView.setImageResource(R.drawable.iphone);
        holder.mTitle.setText(phone.getBrand());
        holder.mDescription.setText(phone.getDeviceName());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {

                Bundle args = new Bundle();

                args.putSerializable("phone", phones.get(position));


                // Check if you are in Home or Favourite Fragment
//                if (phones.equals(FavouritesFragment.))
                    Navigation.findNavController(view).navigate(R.id.recycler_view_to_details, args);
//                else
//                    Navigation.findNavController(view).navigate(R.id.action_nav_home_to_detailsFragment, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public void updatePhonesList(ArrayList<Phone> phones) {
        this.phones = phones;
        this.notifyDataSetChanged();
    }
}
