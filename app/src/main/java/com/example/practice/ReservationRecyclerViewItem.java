package com.example.practice;

import android.graphics.drawable.Drawable;

public class ReservationRecyclerViewItem {

    private Drawable reservation_image;
    private String reservation_name;
    private String reservation_description;
    private String reservation_price;

    public ReservationRecyclerViewItem(Drawable reservation_image, String reservation_name, String reservation_description, String reservation_price) {
        this.reservation_image = reservation_image;
        this.reservation_name = reservation_name;
        this.reservation_description = reservation_description;
        this.reservation_price = reservation_price;
    }

    public Drawable getReservation_image() {
        return reservation_image;
    }

    public void setReservation_image(Drawable reservation_image) {
        this.reservation_image = reservation_image;
    }

    public String getReservation_name() {
        return reservation_name;
    }

    public void setReservation_name(String reservation_name) {
        this.reservation_name = reservation_name;
    }

    public String getReservation_description() {
        return reservation_description;
    }

    public void setReservation_description(String reservation_description) {
        this.reservation_description = reservation_description;
    }

    public String getReservation_price() {
        return reservation_price;
    }

    public void setReservation_price(String reservation_price) {
        this.reservation_price = reservation_price;
    }
}
