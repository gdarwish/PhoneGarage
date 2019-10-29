package com.androidproject.mrrobot;

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

    public Phone(String deviceName, String brand, String cpu, String internal, String os) {
        this.deviceName = deviceName;
        this.brand = brand;
        this.cpu = cpu;
        this.internal = internal;
        this.os = os;
    }

    public String getDetailsFormatted() {
        return String.format("Device: %s\nBrand: %s\nCPU: %s\nInternal: %s\nOS: %s\n", deviceName, brand, cpu, internal, os);
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
}
