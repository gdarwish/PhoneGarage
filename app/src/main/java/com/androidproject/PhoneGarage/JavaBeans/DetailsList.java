package com.androidproject.PhoneGarage.JavaBeans;

public class DetailsList {

    private String title;
    private String value;
    private String url;

    public DetailsList(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public DetailsList(String title, String value, String url) {
        this.title = title;
        this.value = value;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
