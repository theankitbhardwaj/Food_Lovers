package com.bhardwaj.foodlovers.Fragments;


import android.content.Context;
import android.graphics.ColorSpace;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhardwaj.foodlovers.API.RetroAPI;
import com.bhardwaj.foodlovers.Adapters.BreakfastAdapter;
import com.bhardwaj.foodlovers.Adapters.OfferAdapter;
import com.bhardwaj.foodlovers.Adapters.RestaurantAdapter;
import com.bhardwaj.foodlovers.Adapters.TopPickAdapter;
import com.bhardwaj.foodlovers.Models.ModelBreakfast;
import com.bhardwaj.foodlovers.Models.ModelFood;
import com.bhardwaj.foodlovers.Models.ModelOffers;
import com.bhardwaj.foodlovers.Models.ModelRestaurants;
import com.bhardwaj.foodlovers.Models.ModelTopPicks;
import com.bhardwaj.foodlovers.R;
import com.bhardwaj.foodlovers.DB.SharedPreferenceConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public Context context;
    RecyclerView res_recyclerView, toppicks_recyclerView, offer_recyclerView, breakfast_recyclerView;
    RestaurantAdapter restaurantAdapter;
    TopPickAdapter topPickAdapter;
    BreakfastAdapter breakfastAdapter;
    OfferAdapter offerAdapter;
    public HomeFragment() {
        // Required empty public constructor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final List<ModelFood> allFood = new ArrayList<>();
        String baseUrl = "https://theankitbhardwaj.github.io/jsonHost/";
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        res_recyclerView = view.findViewById(R.id.recyclerView_nearBy);
        toppicks_recyclerView = view.findViewById(R.id.recyclerView_topPicks);
        offer_recyclerView = view.findViewById(R.id.recyclerView_offers);
        breakfast_recyclerView = view.findViewById(R.id.recyclerView_breakfast);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroAPI service = retrofit.create(RetroAPI.class);
        Call<List<ModelTopPicks>> callTP = service.getTopPicks();
        Call<List<ModelBreakfast>> callBF = service.getBreakfast();
        Call<List<ModelOffers>> callOff = service.getOffers();
        Call<List<ModelRestaurants>> callRes = service.getRestaurants();

        callOff.enqueue(new Callback<List<ModelOffers>>() {

            @Override
            public void onResponse(Call<List<ModelOffers>> call, Response<List<ModelOffers>> response) {
                generateOffList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelOffers>> call, Throwable t) {

            }

        });

        callTP.enqueue(new Callback<List<ModelTopPicks>>() {

            @Override
            public void onResponse(Call<List<ModelTopPicks>> call, Response<List<ModelTopPicks>> response) {
                generateTPList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelTopPicks>> call, Throwable t) {
                Log.d("fetch", t.getLocalizedMessage());
            }
        });

        callBF.enqueue(new Callback<List<ModelBreakfast>>() {

            @Override
            public void onResponse(Call<List<ModelBreakfast>> call, Response<List<ModelBreakfast>> response) {
                generateBFList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelBreakfast>> call, Throwable t) {
                Log.d("fetch", t.getLocalizedMessage());
            }
        });


        callRes.enqueue(new Callback<List<ModelRestaurants>>() {

            @Override
            public void onResponse(Call<List<ModelRestaurants>> call, Response<List<ModelRestaurants>> response) {
                generateResList(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelRestaurants>> call, Throwable t) {

            }
        });
    }

    private void generateResList(List<ModelRestaurants> modelRestaurants) {
        if (modelRestaurants != null) {
            restaurantAdapter = new RestaurantAdapter(modelRestaurants, getContext());
            LinearLayoutManager resLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            res_recyclerView.setLayoutManager(resLayoutManager);
            res_recyclerView.setAdapter(restaurantAdapter);
        }
    }

    private void generateOffList(List<ModelOffers> modelOffers) {
        if (modelOffers != null) {
            offerAdapter = new OfferAdapter(modelOffers, getContext());
            RecyclerView.LayoutManager offLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            offer_recyclerView.setLayoutManager(offLayoutManager);
            offer_recyclerView.setAdapter(offerAdapter);
        }
    }

    private void generateBFList(List<ModelBreakfast> modelBreakfast) {
        breakfastAdapter = new BreakfastAdapter(modelBreakfast, getContext());
        LinearLayoutManager bfLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        breakfast_recyclerView.setLayoutManager(bfLayoutManager);
        breakfast_recyclerView.setAdapter(breakfastAdapter);
    }

    private void generateTPList(List<ModelTopPicks> modelTopPicks) {
        //if(modelTopPicks != null){
        topPickAdapter = new TopPickAdapter(modelTopPicks, getContext());
        LinearLayoutManager tpLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        toppicks_recyclerView.setLayoutManager(tpLayoutManager);
        toppicks_recyclerView.setAdapter(topPickAdapter);
        //}

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        ;
        super.onSaveInstanceState(outState);
    }
}
