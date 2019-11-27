package com.androidproject.PhoneGarage.JavaBeans;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Switch;

public class ModeSharePref {

    SharedPreferences sharedPreferences;

    public ModeSharePref(Context context) {
        sharedPreferences = context.getSharedPreferences("filename", Context.MODE_PRIVATE);
    }

    // this method saves the nightMode status
    public void setNightMode(boolean state) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    // this method will load the stats
    public boolean loadNightMode() {
        return sharedPreferences.getBoolean("NightMode", false);
    }

    public boolean onOff() {
       return sharedPreferences.getBoolean("NightMode", false);
    }


}
