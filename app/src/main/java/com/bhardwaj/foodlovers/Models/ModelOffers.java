package com.bhardwaj.foodlovers.Models;

import com.google.gson.annotations.SerializedName;

public class ModelOffers {
    @SerializedName("imgUrl")
    private String dishImage;
    @SerializedName("offer")
    private String offer;
    @SerializedName("offerCondition")
    private String offerCondition;

    public ModelOffers(String dishImage, String offer, String offerCondition) {
        this.dishImage = dishImage;
        this.offer = offer;
        this.offerCondition = offerCondition;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getDishImage() {
        return dishImage;
    }

    public void setDishImage(String dishImage) {
        this.dishImage = dishImage;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(String offerCondition) {
        this.offerCondition = offerCondition;
    }

}
