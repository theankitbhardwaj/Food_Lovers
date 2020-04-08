package com.bhardwaj.foodlovers;

public class ModelTopPicks {
    private int dishImage;
    private String dishRating;
    private String dishName;
    private String dishPrice;

    public ModelTopPicks(int dishImage, String dishName, String dishRating, String dishPrice) {
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

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
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
