package com.androidproject.PhoneGarage.JavaBeans;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.PhoneGarage.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author gaithdarwish
 */
public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView mImageView;
    public TextView mTitle;
    public TextView mDescription;
    ItemClickListener itemClickListener;

    /**
     * @param itemView
     */
    public MyHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageView = itemView.findViewById(R.id.image);
        this.mTitle = itemView.findViewById(R.id.title);
        this.mDescription = itemView.findViewById(R.id.description);

        itemView.setOnClickListener(this);
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {

        this.itemClickListener.onItemClickListner(view, getLayoutPosition());
    }

    /**
     * @param ic
     */
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
