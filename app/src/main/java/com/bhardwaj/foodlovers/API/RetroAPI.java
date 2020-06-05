package com.bhardwaj.foodlovers.API;

import com.bhardwaj.foodlovers.Models.ModelBreakfast;
import com.bhardwaj.foodlovers.Models.ModelFood;
import com.bhardwaj.foodlovers.Models.ModelOffers;
import com.bhardwaj.foodlovers.Models.ModelRestaurants;
import com.bhardwaj.foodlovers.Models.ModelTopPicks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroAPI {
    @GET("offers")
    Call<List<ModelOffers>> getOffers();

    @GET("top")
    Call<List<ModelTopPicks>> getTopPicks();

    @GET("breakfast")
    Call<List<ModelBreakfast>> getBreakfast();

    @GET("restaurants")
    Call<List<ModelRestaurants>> getRestaurants();

    @GET("food")
    Call<List<ModelFood>> getFood();
}
