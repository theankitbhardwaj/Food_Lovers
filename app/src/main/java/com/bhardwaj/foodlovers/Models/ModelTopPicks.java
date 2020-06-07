package com.bhardwaj.foodlovers.Models;

import com.google.gson.annotations.SerializedName;

public class ModelTopPicks {
    @SerializedName("imgUrl")
    private String dishImage;
    @SerializedName("name")
    private String dishName;
    @SerializedName("rating")
    private String dishRating;
    @SerializedName("desc")
    private String dishDesc;
    @SerializedName("price")
    private String dishPrice;
    @SerializedName("quantity")
    private String dishQuantity;

    public ModelTopPicks(String dishImage, String dishName, String dishRating, String dishDesc, String dishPrice, String dishQuantity) {
        this.dishImage = dishImage;
        this.dishName = dishName;
        this.dishRating = dishRating;
        this.dishPrice = dishPrice;
        this.dishDesc = dishDesc;
        this.dishQuantity = dishQuantity;
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

    public String getDishDesc() {
        return dishDesc;
    }

    public void setDishDesc(String dishDesc) {
        this.dishDesc = dishDesc;
    }

    public String getDishQuantity() {
        return dishQuantity;
    }

    public void setDishQuantity(String dishQuantity) {
        this.dishQuantity = dishQuantity;
    }
}
