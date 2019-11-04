package com.androidproject.PhoneGarage;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone implements Serializable {
    /*
    DeviceName
    Brand
    Size
    resolution
    Dimensions
    Weight
    Type [screen]
    card_slot
    Wlan
    Bluetooth
    Gps
    battery_c
    Colors
    sensors
    Cpu
    Internal
    Os
    Video
    Gpu
    Features [camera]
    Single [front camera]
    Tripple [primary camera]
    Charging
     */

    @SerializedName("DeviceName")
    private String deviceName;
    @SerializedName("Brand")
    private String brand;
    private String cpu;
    private String internal;
    private String os;
    private int image;

    public Phone(String deviceName, String brand, String cpu, String internal, String os, int image) {
        this.deviceName = deviceName;
        this.brand = brand;
        this.cpu = cpu;
        this.internal = internal;
        this.os = os;
        this.image = image;
    }

    public String getDetailsFormatted() {
        return String.format("Device: %s\nBrand: %s\nCPU: %s\nInternal: %s\nOS: %s\nImage:%d", deviceName, brand, cpu, internal, os, image);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getBrand() {
        return brand;
    }

    public String getCpu() {
        return cpu;
    }

    public String getInternal() {
        return internal;
    }

    public String getOs() {
        return os;
    }

    public int getImage() {
        return image;
    }
}
