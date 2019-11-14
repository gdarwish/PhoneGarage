package com.androidproject.PhoneGarage;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Phone implements Serializable {
    /*
    imageUrl
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
    Dual [primary camera]
    Tripple [primary camera]
    Charging
     */

    private String imageUrl;

    @SerializedName("DeviceName")
    private String deviceName;
    @SerializedName("Brand")
    private String brand;
    private String size;
    private String resolution;
    private String dimensions;
    private String weight;
    @SerializedName("type")
    private String screenType;
    @SerializedName("card_slot")
    private String cardSlot;
    private String wlan;
    private String bluetooth;
    private String gps;
    @SerializedName("battery_c")
    private String batteryCapacity;
    private String colors;
    private String sensors;
    private String cpu;
    private String internal;
    private String os;
    private String video;
    private String gpu;
    @SerializedName("features")
    private String cameraFeature;
    @SerializedName("single")
    private String frontCamera;
    @SerializedName("dual_")
    private String dualCamera;
    @SerializedName("triple")
    private String tripleCamera;
    private String charging;


//    public Phone(String imageUrl, String deviceName, String brand, String cpu, String internal, String os) {
//        this.imageUrl = imageUrl;
//        this.deviceName = deviceName;
//        this.brand = brand;
//        this.cpu = cpu;
//        this.internal = internal;
//        this.os = os;
//    }


    public Phone(String imageUrl, String deviceName, String brand, String size, String resolution, String dimensions, String weight, String screenType, String cardSlot, String wlan, String bluetooth, String gps, String batteryCapacity, String colors, String sensors, String cpu, String internal, String os, String video, String gpu, String cameraFeature, String frontCamera, String dualCamera, String tripleCamera, String charging) {
        this.imageUrl = imageUrl;
        this.deviceName = deviceName;
        this.brand = brand;
        this.size = size;
        this.resolution = resolution;
        this.dimensions = dimensions;
        this.weight = weight;
        this.screenType = screenType;
        this.cardSlot = cardSlot;
        this.wlan = wlan;
        this.bluetooth = bluetooth;
        this.gps = gps;
        this.batteryCapacity = batteryCapacity;
        this.colors = colors;
        this.sensors = sensors;
        this.cpu = cpu;
        this.internal = internal;
        this.os = os;
        this.video = video;
        this.gpu = gpu;
        this.cameraFeature = cameraFeature;
        this.frontCamera = frontCamera;
        this.dualCamera = dualCamera;
        this.tripleCamera = tripleCamera;
        this.charging = charging;
    }

    public String getDetailsFormatted() {
        return String.format("Device: %s\nBrand: %s\nCPU: %s\nInternal: %s\nOS: %s\nSize: %s\nResolution: %s\nDimensions: %s\nWeight: %s\nScreen Type: %s\nCard Slot: %s\nWlan: %s\nBluetooth: %s\nGPS: %s\nBattery: %s\nColors: %s\nSensors: %s\nCPU: %s\nInternal: %s\nOS: %s\nVideo: %s\nGPU: %s\nCamera Feature: %s\nFront Camera: %s\nDual Camera: %s\nTriple Camera: %s\nCharging: %s", deviceName, brand, cpu, internal, os, size, resolution, dimensions, weight, screenType, cardSlot, wlan, bluetooth, gps, batteryCapacity, colors, sensors, cpu, internal, os, video, gpu, cameraFeature, frontCamera, dualCamera, tripleCamera, charging);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSize() {
        return size;
    }

    public String getResolution() {
        return resolution;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getWeight() {
        return weight;
    }

    public String getScreenType() {
        return screenType;
    }

    public String getCardSlot() {
        return cardSlot;
    }

    public String getWlan() {
        return wlan;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public String getGps() {
        return gps;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public String getColors() {
        return colors;
    }

    public String getSensors() {
        return sensors;
    }

    public String getVideo() {
        return video;
    }

    public String getGpu() {
        return gpu;
    }

    public String getCameraFeature() {
        return cameraFeature;
    }

    public String getFrontCamera() {
        return frontCamera;
    }

    public String getDualCamera() {
        return dualCamera;
    }

    public String getTripleCamera() {
        return tripleCamera;
    }

    public String getCharging() {
        return charging;
    }
}
