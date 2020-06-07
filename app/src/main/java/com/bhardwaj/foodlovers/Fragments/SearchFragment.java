package com.bhardwaj.foodlovers.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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

import com.bhardwaj.foodlovers.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    public EditText searchEditText;
    public ConstraintLayout layoutSearch;
    private TextView searchDesc;
    private ImageView searchIco;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchEditText = view.findViewById(R.id.editText_search);
        layoutSearch = view.findViewById(R.id.layoutBeforeSearch);
        searchIco = view.findViewById(R.id.img_searchIco);
        searchDesc = view.findViewById(R.id.txtSearchDesc);
        searchEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    Toast.makeText(getContext(), "Search started...", Toast.LENGTH_SHORT).show();
                    layoutSearch.removeView(searchDesc);
                    searchIco.setImageResource(R.drawable.load);
                }
                return false;
            }
        });
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
