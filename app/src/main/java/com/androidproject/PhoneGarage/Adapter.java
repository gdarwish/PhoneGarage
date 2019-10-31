package com.androidproject.PhoneGarage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<MyHolder> {

    Context context;
    ArrayList<Phone> phones;


    public Adapter(Context context, ArrayList<Phone> phones) {
        this.context = context;
        this.phones = phones;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_example, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

//        holder.mImageView.setImageResource(phones.get(position).getImage());
        holder.mTitle.setText(phones.get(position).getBrand());
        holder.mDescription.setText(phones.get(position).getDeviceName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {

                String gtitle = phones.get(position).getBrand();
                String gDesc = phones.get(position).getDeviceName();
//                int mImage = phones.get(position).getImage();

                Bundle args = new Bundle();
                args.putString("title", gtitle);
                args.putString("desc", gDesc);
//                args.putInt("img", mImage);
                args.putSerializable("phone", phones.get(position));

                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_detailsFragment6, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }
}
