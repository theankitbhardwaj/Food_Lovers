package com.bhardwaj.foodlovers.Models;

import com.google.gson.annotations.SerializedName;

public class ModelRestaurants {
    @SerializedName("imgUrl")
    private String resImage;
    @SerializedName("name")
    private String resName;

    public ModelRestaurants(String resImage, String resName) {
        this.resImage = resImage;
        this.resName = resName;
    }

    public String getResImage() {
        return resImage;
    }

    public void setResImage(String resImage) {
        this.resImage = resImage;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }
}
