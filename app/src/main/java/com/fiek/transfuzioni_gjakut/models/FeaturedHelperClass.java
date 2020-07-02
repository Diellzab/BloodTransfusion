package com.fiek.transfuzioni_gjakut.models;

public class FeaturedHelperClass {

    int img;
    String title, description;

    public FeaturedHelperClass(int img, String title, String description) {
        this.img = img;
        this.title = title;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
