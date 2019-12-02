package com.androidproject.PhoneGarage.JavaBeans;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class will read JSON file and store it inside a list
 *
 * @author Ali Dali
 */
public class Data {

    private ArrayList<Phone> phones;
    private String jsonPhones;

    private static Data data;

    public static Data getInstance(Context context) {
        if (data == null)
            data = new Data(context);
        return data;
    }

    private Data(Context context) {

        Gson gson = new Gson();

        if (jsonPhones == null)
            jsonPhones = generateJSON(context);

        if (jsonPhones != null) {
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();

            phones = gson.fromJson(jsonPhones, phoneType);

        }

    }

    public ArrayList<Phone> getPhonesList() {
        return phones;
    }

    /**
     *
     * This will read data from json file and store in string
     * @param context
     * @return String json data
     * @author Ali Dali
     */
    private String generateJSON(Context context) {

        try {

            InputStream is = context.getAssets().open("phones.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            return new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
