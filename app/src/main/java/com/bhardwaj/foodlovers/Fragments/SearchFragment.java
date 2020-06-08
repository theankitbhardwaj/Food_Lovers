package com.bhardwaj.foodlovers.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.foodlovers.API.RetroAPI;
import com.bhardwaj.foodlovers.Adapters.SearchAdapter;
import com.bhardwaj.foodlovers.Models.ModelFood;
import com.bhardwaj.foodlovers.Models.ModelTopPicks;
import com.bhardwaj.foodlovers.R;

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
public class SearchFragment extends Fragment {
    public EditText searchEditText;
    public ConstraintLayout layoutSearch;
    private TextView searchDesc;
    private ImageView searchIco;
    static String searchFor = "";
    Call<List<ModelFood>> callFood;
    List<ModelFood> fetchedList;
    private RecyclerView rvSearch;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String baseUrl = "https://theankitbhardwaj.github.io/jsonHost/";
        searchEditText = view.findViewById(R.id.editText_search);
        layoutSearch = view.findViewById(R.id.layoutBeforeSearch);
        searchIco = view.findViewById(R.id.img_searchIco);
        searchDesc = view.findViewById(R.id.txtSearchDesc);
        rvSearch = view.findViewById(R.id.rv_search);
        rvSearch.setVisibility(View.INVISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetroAPI service = retrofit.create(RetroAPI.class);
        callFood = service.getFood();
        callFood.enqueue(new Callback<List<ModelFood>>() {
            @Override
            public void onResponse(Call<List<ModelFood>> call, Response<List<ModelFood>> response) {
                fetchedList = response.body();
            }

            @Override
            public void onFailure(Call<List<ModelFood>> call, Throwable t) {

            }
        });
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    layoutSearch.removeView(searchDesc);
                    layoutSearch.removeView(searchIco);
                    layoutSearch.setVisibility(View.INVISIBLE);
                    rvSearch.setVisibility(View.VISIBLE);
                    searchFor = searchEditText.getText().toString();
                    generateList(searchFor);
                }
                return false;
            }
        });


    }

    private void generateList(String s) {
        List<ModelFood> searchRes = new ArrayList<>();
        searchRes.clear();
        if (s.isEmpty()) {
            rvSearch.setVisibility(View.INVISIBLE);
            rvSearch.setEnabled(false);
            layoutSearch.setVisibility(View.VISIBLE);
            layoutSearch.addView(searchDesc);
            layoutSearch.addView(searchIco);
        } else {
            rvSearch.setVisibility(View.VISIBLE);
            rvSearch.setEnabled(true);
            layoutSearch.setVisibility(View.INVISIBLE);
        }
        if (rvSearch.isEnabled()) {
            for (int i = 0; i < fetchedList.size(); i++) {
                if (fetchedList.get(i).getDishName().toUpperCase().contains(s.toUpperCase()) || fetchedList.get(i).getDishDesc().toUpperCase().contains(s.toUpperCase())) {
                    searchRes.add(fetchedList.get(i));
                }
            }
            if (searchRes != null) {
                SearchAdapter adapter = new SearchAdapter(getContext(), searchRes);
                rvSearch.setLayoutManager(new GridLayoutManager(getContext(), 2));
                rvSearch.setAdapter(adapter);
            }
        }
    }

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

}
