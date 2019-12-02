package com.androidproject.PhoneGarage.HelperAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

/**
 * @author gaithdarwish
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<MyHolder> {

    Context context;
    ArrayList<Phone> phones;
    ViewGroup parent;
    int layout;

    /**
     * @param context
     * @param phones
     * @author Ghaith
     */
    public RecyclerViewAdapter(Context context, ArrayList<Phone> phones) {
        this.context = context;
        this.phones = phones;

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean selectLayout = sharedPreferences.getBoolean("layout", false);
        // if selectLayout is true set the layout to recycler_view_row, else set it to recycler_view_row2
        if (selectLayout)
            layout = R.layout.recycler_view_row;
        else
            layout = R.layout.recycler_view_row2;

    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        this.parent = parent;

        return new MyHolder(view);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {

        final Phone phone = phones.get(position);

        if (holder.mImageView != null)
            Picasso.get().load(phone.getImageUrl()[0]).placeholder(R.drawable.iphone).into(holder.mImageView);

        holder.mTitle.setText(phone.getBrand());
        holder.mDescription.setText(phone.getDeviceName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListner(View view, int position) {
                Bundle args = new Bundle();
                args.putSerializable("phone", phones.get(position));
                Navigation.findNavController(view).navigate(R.id.nav_details, args);
            }
        });
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return phones.size();
    }

    /**
     * @param phones
     */
    public void updatePhonesList(ArrayList<Phone> phones) {
        this.phones = phones;
        this.notifyDataSetChanged();
    }
}
