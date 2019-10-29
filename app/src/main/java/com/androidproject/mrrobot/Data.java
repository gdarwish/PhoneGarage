package com.androidproject.mrrobot;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Data {

    private ArrayList<Phone> phones;
    private String jsonPhones;

    private static Data data;

    public static Data getInstance(Context context) {
        if (data != null)
            return data;
        return new Data(context);
    }

    private Data(Context context) {

        Gson gson = new Gson();

        if (jsonPhones == null)
            jsonPhones = generateJSON(context);

        if (jsonPhones != null) {
            Type phoneType = new TypeToken<ArrayList<Phone>>() {
            }.getType();

            phones = gson.fromJson(jsonPhones, phoneType);


//            for (Phone phone: phones) {
//                Log.d("PHONES", phone.getDetailsFormatted());
//            }
        }

    }

    public ArrayList<Phone> getPhonesList() {
        return phones;
    }


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
