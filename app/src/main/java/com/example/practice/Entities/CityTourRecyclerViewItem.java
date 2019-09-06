package com.example.practice.Entities;

public class CityTourRecyclerViewItem {

    private int id;
    private String name, address;
    private int rate;
    private String image;

    public CityTourRecyclerViewItem(int id, String name, String address, int rate, String image) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
