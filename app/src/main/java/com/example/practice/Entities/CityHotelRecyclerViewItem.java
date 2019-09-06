package com.example.practice.Entities;

import android.graphics.drawable.Drawable;

public class CityHotelRecyclerViewItem {

    private int id, price;
    private String name, grade, location, image;

    public CityHotelRecyclerViewItem(int id, String name, String grade, String location, int price, String image) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.location = location;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
