package com.bhardwaj.foodlovers;

public class ModelRestaurants {
    private int resImage;
    private String resName;

    public ModelRestaurants(int resImage, String resName) {
        this.resImage = resImage;
        this.resName = resName;
    }

    public int getResImage() {
        return resImage;
    }

    public void setResImage(int resImage) {
        this.resImage = resImage;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }
}
