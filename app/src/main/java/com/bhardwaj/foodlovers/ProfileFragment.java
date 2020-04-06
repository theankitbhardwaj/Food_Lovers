package com.bhardwaj.foodlovers;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    public TextView usernameTextView, emailTextView, mobTextView, bt_logout, contactTextView, helpTextView;
    SharedPreferenceConfig preferenceConfig;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        preferenceConfig = new SharedPreferenceConfig(getContext());
        String[] data = preferenceConfig.readUserData();
        usernameTextView = view.findViewById(R.id.textView_Username);
        emailTextView = view.findViewById(R.id.textView_email);
        mobTextView = view.findViewById(R.id.textView_mob);
        bt_logout = view.findViewById(R.id.bt_logout);
        contactTextView = view.findViewById(R.id.textView_contact);
        helpTextView = view.findViewById(R.id.textView_help);
        super.onViewCreated(view, savedInstanceState);
        usernameTextView.setText(data[0]);
        emailTextView.setText(data[2]);
        mobTextView.setText("+91" + data[1]);

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferenceConfig.writeLoginStatus(false);
                startActivity(new Intent(getContext(), SignInActivity.class));
            }
        });

        contactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.github.com/theankitbhardwaj");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        helpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://www.github.com/theankitbhardwaj");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
