package com.bhardwaj.foodlovers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        final ArrayList<String> data = getIntent().getStringArrayListExtra("userData");
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList("userData", data);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navigation_home) {
                    HomeFragment fragment = new HomeFragment();
                    fragment.setArguments(bundle);
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment, fragment);
                    transaction.commit();
                } else if (id == R.id.navigation_search) {
                    SearchFragment fragment = new SearchFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment, fragment);
                    transaction.commit();
                } else if (id == R.id.navigation_cart) {
                    CartFragment fragment = new CartFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment, fragment);
                    transaction.commit();
                } else if (id == R.id.navigation_profile) {
                    ProfileFragment fragment = new ProfileFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFragment, fragment);
                    transaction.commit();
                }
                return true;
            }
        });

        bottomNav.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
