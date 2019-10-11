package com.example.practice.Entities;

public class MainRecyclerViewItem {

    private String main_image;
    private String main_description1;
    private String main_description2;

    public MainRecyclerViewItem(String main_image, String main_description1, String main_description2) {
        this.main_image = main_image;
        this.main_description1 = main_description1;
        this.main_description2 = main_description2;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getMain_description1() {
        return main_description1;
    }

    public void setMain_description1(String main_description1) {
        this.main_description1 = main_description1;
    }

    public String getMain_description2() {
        return main_description2;
    }

    public void setMain_description2(String main_description2) {
        this.main_description2 = main_description2;
    }
}
