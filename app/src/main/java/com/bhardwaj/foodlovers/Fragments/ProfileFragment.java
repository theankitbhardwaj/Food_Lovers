package com.bhardwaj.foodlovers.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.foodlovers.R;
import com.bhardwaj.foodlovers.DB.SharedPreferenceConfig;
import com.bhardwaj.foodlovers.Activities.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    static String address = "Address Here";
    public TextView usernameTextView, emailTextView, bt_logout, contactTextView, helpTextView;
    EditText et_address;
    FirebaseAuth mAuth;
    SharedPreferenceConfig preferenceConfig;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        preferenceConfig = new SharedPreferenceConfig(getContext());
        usernameTextView = view.findViewById(R.id.textView_Username);
        emailTextView = view.findViewById(R.id.textView_email);
        bt_logout = view.findViewById(R.id.bt_logout);
        contactTextView = view.findViewById(R.id.textView_contact);
        helpTextView = view.findViewById(R.id.textView_help);
        et_address = view.findViewById(R.id.etAddress);
        if (et_address != null) {
            et_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    preferenceConfig.writeUserAddress(et_address.getText().toString());
                }
            });
            et_address.setText(preferenceConfig.readUserAddress());
        }

        super.onViewCreated(view, savedInstanceState);
        FirebaseUser user = mAuth.getCurrentUser();
        usernameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //preferenceConfig.writeLoginStatus(false);
                Toast.makeText(getContext(), "Logged Out.", Toast.LENGTH_SHORT);
                mAuth.signOut();
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
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
