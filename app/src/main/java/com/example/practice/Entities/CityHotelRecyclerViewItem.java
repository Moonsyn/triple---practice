package com.example.practice.Entities;

import android.graphics.drawable.Drawable;

public class CityHotelRecyclerViewItem {

    private Drawable hotelImage;
    private String hotelName, hotelCategory, hotelPrice;

    public CityHotelRecyclerViewItem(Drawable hotelImage, String hotelName, String hotelCategory, String hotelPrice) {
        this.hotelImage = hotelImage;
        this.hotelName = hotelName;
        this.hotelCategory = hotelCategory;
        this.hotelPrice = hotelPrice;
    }

    public Drawable getHotelImage() {
        return hotelImage;
    }

    public void setHotelImage(Drawable hotelImage) {
        this.hotelImage = hotelImage;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelCategory() {
        return hotelCategory;
    }

    public void setHotelCategory(String hotelCategory) {
        this.hotelCategory = hotelCategory;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }
}
