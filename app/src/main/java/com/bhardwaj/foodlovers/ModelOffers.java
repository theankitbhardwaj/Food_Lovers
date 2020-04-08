package com.bhardwaj.foodlovers;

public class ModelOffers {
    private int dishImage;
    private String offer;
    private String offerCondition;

    public ModelOffers(int dishImage, String offer, String offerCondition) {
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

    public int getDishImage() {
        return dishImage;
    }

    public void setDishImage(int dishImage) {
        this.dishImage = dishImage;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(String offerCondition) {
        this.offerCondition = offerCondition;
    }

}
