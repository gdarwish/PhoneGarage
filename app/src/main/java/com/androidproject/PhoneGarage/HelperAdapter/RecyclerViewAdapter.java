package com.androidproject.PhoneGarage.HelperAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.util.Log;
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

    int layout;

    public RecyclerViewAdapter(Context context, ArrayList<Phone> phones) {
        this.context = context;
        this.phones = phones;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean selectLayout = sharedPreferences.getBoolean("layout", false);
        if (selectLayout)
            layout = R.layout.recycler_view_row;
        else
            layout = R.layout.recycler_view_row2;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        this.parent = parent;

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        Phone phone = phones.get(position);

        Picasso.get().load(phone.getImageUrl()).placeholder(R.drawable.iphone).into(holder.mImageView);

        holder.mTitle.setText(phone.getBrand());
        holder.mDescription.setText(phone.getDeviceName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {


                Bundle args = new Bundle();
                args.putSerializable("phone", phones.get(position));
//                Log.e("PHONE", phones.get(position).getDetailsFormatted());
                // Check if you are in Home or Favourite Fragment
                Navigation.findNavController(view).navigate(R.id.nav_details, args);
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

    public void updatePhonesList(ArrayList<Phone> phones) {
        this.phones = phones;
        this.notifyDataSetChanged();
    }
}
