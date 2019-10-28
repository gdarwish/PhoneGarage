package com.androidproject.mrrobot;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTitle;
    public  TextView mDescription;

    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image);
        this.mTitle = itemView.findViewById(R.id.title);
        this.mDescription = itemView.findViewById(R.id.description);
    }
}
