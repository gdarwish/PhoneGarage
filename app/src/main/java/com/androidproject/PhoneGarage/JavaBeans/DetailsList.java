package com.androidproject.PhoneGarage.JavaBeans;

/**
 * @author gaithdarwish
 */
public class DetailsList {

    private String title;
    private String value;
    private String url;

    /**
     * @param title
     * @param value
     */
    public DetailsList(String title, String value) {
        this.title = title;
        this.value = value;
    }

    /**
     * @param title
     * @param value
     * @param url
     */
    public DetailsList(String title, String value, String url) {
        this.title = title;
        this.value = value;
        this.url = url;
    }

    /**
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return
     */
    public String getValue() {
        return value;
    }
}
