package com.androidproject.PhoneGarage.JavaBeans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * JSON objects will be stored as Phone object
 * @author Ali Dali
 */
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

    private String[] imageUrl;

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

    private String primaryCamera;


    public Phone(String[] imageUrl, String deviceName, String brand, String size, String resolution, String dimensions, String weight, String screenType, String cardSlot, String wlan, String bluetooth, String gps, String batteryCapacity, String colors, String sensors, String cpu, String internal, String os, String video, String gpu, String cameraFeature, String frontCamera, String dualCamera, String tripleCamera, String charging) {
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
        return String.format("Images: %s\nDevice: %s\nBrand: %s\nCPU: %s\nInternal: %s\nOS: %s\nSize: %s\nResolution: %s\nDimensions: %s\nWeight: %s\nScreen Type: %s\nCard Slot: %s\nWlan: %s\nBluetooth: %s\nGPS: %s\nBattery: %s\nColors: %s\nSensors: %s\nCPU: %s\nInternal: %s\nOS: %s\nVideo: %s\nGPU: %s\nCamera Feature: %s\nFront Camera: %s\nDual Camera: %s\nTriple Camera: %s\nCharging: %s",  imageUrl, deviceName, brand, cpu, internal, os, size, resolution, dimensions, weight, screenType, cardSlot, wlan, bluetooth, gps, batteryCapacity, colors, sensors, cpu, internal, os, video, gpu, cameraFeature, frontCamera, dualCamera, tripleCamera, charging);
    }

    public String getDeviceName() {
        return deviceName != null ? deviceName : "N/A";
    }

    public String getBrand() {
        return brand  != null ? brand : "N/A";
    }

    public String getCpu() {
        return cpu  != null ? cpu : "N/A";
    }

    public String getInternal() {
        return internal  != null ? internal : "N/A";
    }

    public String getOs() {
        return os  != null ? os : "N/A";
    }

    public String[] getImageUrl() {
        return imageUrl != null ? imageUrl : new String[]{"https://gdarwish.scweb.ca/PHP/phoneGarage/phoneImage.png"};
    }

    public String getSize() {
        return size  != null ? size : "N/A";
    }

    public String getResolution() {
        return resolution  != null ? resolution : "N/A";
    }

    public String getDimensions() {
        return dimensions  != null ? dimensions : "N/A";
    }

    public String getWeight() {
        return weight  != null ? weight : "N/A";
    }

    public String getScreenType() {
        return screenType != null ? screenType : "N/A";
    }

    public String getCardSlot() {
        return cardSlot != null ? cardSlot : "N/A";
    }

    public String getWlan() {
        return wlan  != null ? wlan : "N/A";
    }

    public String getBluetooth() {
        return bluetooth  != null ? bluetooth : "N/A";
    }

    public String getGps() {
        return gps != null ? gps : "N/A";
    }

    public String getBatteryCapacity() {
        return batteryCapacity != null ? batteryCapacity : "N/A";
    }

    public String getColors() {
        return colors != null ? colors : "N/A";
    }

    public String getSensors() {
        return sensors != null ? sensors : "N/A";
    }

    public String getVideo() {
        return video != null ? video : "N/A";
    }

    public String getGpu() {
        return gpu != null ? gpu : "N/A";
    }

    public String getCameraFeature() {
        return cameraFeature != null ? cameraFeature : "N/A";
    }

    public String getFrontCamera() {
        return frontCamera != null ? frontCamera : "N/A";
    }

    public String getDualCamera() {
        return dualCamera != null ? dualCamera : "N/A";
    }

    public String getTripleCamera() {
        return tripleCamera != null ? tripleCamera : "N/A";
    }

    public String getCharging() {
        return charging != null ? charging : "N/A";
    }

    public String getPrimaryCamera() { return dualCamera != null ? getDualCamera() : getTripleCamera(); }

    public String toString() {
        return deviceName;
    }
}
