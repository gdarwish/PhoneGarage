package com.androidproject.mrrobot;

import android.app.Activity;
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
    Activity activity;
    ArrayList<Model> models;


    public Adapter(Context context, Activity acttivity, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
        this.activity = acttivity;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_example, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        holder.mImageView.setImageResource(models.get(position).getImage());
        holder.mTitle.setText(models.get(position).getTitle());
        holder.mDescription.setText(models.get(position).getDescription());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {

                String gtitle = models.get(position).getTitle();
                String gDesc = models.get(position).getDescription();
                int mImage = models.get(position).getImage();

                Bundle args = new Bundle();
                args.putString("title", gtitle);
                args.putString("desc", gDesc);
                args.putInt("img", mImage);

                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_detailsFragment6, args);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
