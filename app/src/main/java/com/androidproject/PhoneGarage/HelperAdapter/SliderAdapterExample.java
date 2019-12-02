package com.androidproject.PhoneGarage.HelperAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidproject.PhoneGarage.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

/**
 * @author gaithdarwish
 */
public class SliderAdapterExample extends SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {

    private Context context;
    private String[] url;

    /**
     * @param context
     * @param url
     */
    public SliderAdapterExample(Context context, String[] url) {
        this.context = context;
        this.url = url;
    }

    /**
     * @param parent
     * @return
     */
    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    /**
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        for (int i = 0; i <= position; i++) {
            Glide.with(viewHolder.itemView)
                    .load(url[i])
                    .into(viewHolder.imageViewBackground);
        }
    }

    /**
     * @return
     */
    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return url.length;
    }

    /**
     * @author gaithdarwish
     */
    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        /**
         * @param itemView
         */
        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            this.itemView = itemView;

        }
    }
}