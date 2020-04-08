package com.bhardwaj.foodlovers;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public Context context;
    RecyclerView res_recyclerView, toppicks_recyclerView, offer_recyclerView, breakfast_recyclerView;
    RestaurantAdapter restaurantAdapter;
    List<ModelTopPicks> modelTopPicks;
    TopPickAdapter topPickAdapter;
    List<ModelBreakfast> modelBreakfast;
    BreakfastAdapter breakfastAdapter;
    OfferAdapter offerAdapter;
    List<ModelRestaurants> modelRestaurants;
    List<ModelOffers> modelOffers;

    SharedPreferenceConfig preferenceConfig;
    public HomeFragment() {
        // Required empty public constructor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        res_recyclerView = view.findViewById(R.id.recyclerView_nearBy);
        toppicks_recyclerView = view.findViewById(R.id.recyclerView_topPicks);
        offer_recyclerView = view.findViewById(R.id.recyclerView_offers);
        breakfast_recyclerView = view.findViewById(R.id.recyclerView_breakfast);

        modelTopPicks = new ArrayList<>();
        modelTopPicks.add(new ModelTopPicks(R.drawable.steamfood, "Steam-Food", "4.0", "₹ 150"));
        modelTopPicks.add(new ModelTopPicks(R.drawable.pizza, "Pizza", "4.5", "₹ 250"));
        modelTopPicks.add(new ModelTopPicks(R.drawable.pancake, "Pancake", "3.8", "₹ 190"));
        modelTopPicks.add(new ModelTopPicks(R.drawable.kimchi, "Kimchi", "4.2", "₹ 75"));

        topPickAdapter = new TopPickAdapter(modelTopPicks, getContext());

        modelBreakfast = new ArrayList<>();
        modelBreakfast.add(new ModelBreakfast(R.drawable.pasta, "Pasta", "4.0", "₹ 70"));
        modelBreakfast.add(new ModelBreakfast(R.drawable.nuggets, "Veg Nuggets", "4.5", "₹ 120"));
        modelBreakfast.add(new ModelBreakfast(R.drawable.sizzler, "Sizzlers", "3.9", "₹ 100"));
        modelBreakfast.add(new ModelBreakfast(R.drawable.pancake, "Pancake", "3.8", "₹ 190"));

        breakfastAdapter = new BreakfastAdapter(modelBreakfast, getContext());

        modelOffers = new ArrayList<>();
        modelOffers.add(new ModelOffers(R.drawable.steamfood, "Get 50% discount", "on First 2 Orders"));
        modelOffers.add(new ModelOffers(R.drawable.kimchi, "Free delivery", "on order above ₹ 200"));
        modelOffers.add(new ModelOffers(R.drawable.pizza, "Buy 1 Get 2", "from Dominos"));
        modelOffers.add(new ModelOffers(R.drawable.pancake, "Extra 20% off", "on Pancakes"));


        offerAdapter = new OfferAdapter(modelOffers, getContext());

        modelRestaurants = new ArrayList<>();
        modelRestaurants.add(new ModelRestaurants(R.drawable.starbucks, "Starbucks"));
        modelRestaurants.add(new ModelRestaurants(R.drawable.kfc, "KFC"));
        modelRestaurants.add(new ModelRestaurants(R.drawable.pizzahut, "Pizza Hut"));
        modelRestaurants.add(new ModelRestaurants(R.drawable.bk, "Burger King"));
        modelRestaurants.add(new ModelRestaurants(R.drawable.dominos, "Dominos"));

        restaurantAdapter = new RestaurantAdapter(modelRestaurants, getContext());

        LinearLayoutManager resLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager tpLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager offLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager bfLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);

        res_recyclerView.setLayoutManager(resLayoutManager);
        res_recyclerView.setAdapter(restaurantAdapter);

        offer_recyclerView.setLayoutManager(offLayoutManager);
        offer_recyclerView.setAdapter(offerAdapter);

        toppicks_recyclerView.setLayoutManager(tpLayoutManager);
        toppicks_recyclerView.setAdapter(topPickAdapter);

        breakfast_recyclerView.setLayoutManager(bfLayoutManager);
        breakfast_recyclerView.setAdapter(breakfastAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
