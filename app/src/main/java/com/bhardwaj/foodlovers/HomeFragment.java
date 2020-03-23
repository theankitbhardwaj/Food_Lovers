package com.bhardwaj.foodlovers;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    public Button bt_logout;
    public Context context;
    public TextView welcomeText;
    public HomeFragment() {
        // Required empty public constructor;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_logout = view.findViewById(R.id.bt_Logout);
        welcomeText = view.findViewById(R.id.textView_WelcomeText);
        List<String> data = getArguments().getStringArrayList("userData");
        welcomeText.setText("Welcome \n" + data.get(1));
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferenceConfig preferenceConfig = new SharedPreferenceConfig(getContext());
                preferenceConfig.writeLoginStatus(false);
                startActivity(new Intent(getContext(), SignInActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        //List<String> data = getArguments().getStringArrayList("userData");

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
