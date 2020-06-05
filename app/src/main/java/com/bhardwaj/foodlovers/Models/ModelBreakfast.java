package com.bhardwaj.foodlovers.Models;

import com.google.gson.annotations.SerializedName;

public class ModelBreakfast {
    @SerializedName("imgUrl")
    private String dishImage;
    @SerializedName("name")
    private String dishName;
    @SerializedName("rating")
    private String dishRating;
    @SerializedName("price")
    private String dishPrice;

    public ModelBreakfast(String dishImage, String dishName, String dishRating, String dishPrice) {
        this.dishImage = dishImage;
        this.dishName = dishName;
        this.dishRating = dishRating;
        this.dishPrice = dishPrice;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }

    public String getDishRating() {
        return dishRating;
    }

    public void setDishRating(String dishRating) {
        this.dishRating = dishRating;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }
}
