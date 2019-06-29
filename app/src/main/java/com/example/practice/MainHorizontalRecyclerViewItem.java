package com.example.practice;

import android.graphics.drawable.Drawable;

public class MainHorizontalRecyclerViewItem {

    private Drawable city_image;
    private String city_name;

    public MainHorizontalRecyclerViewItem(Drawable city_image, String city_name) {
        this.city_image = city_image;
        this.city_name = city_name;
    }

    public Drawable getCity_image() {
        return city_image;
    }

    public void setCity_image(Drawable city_image) {
        this.city_image = city_image;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }
}
