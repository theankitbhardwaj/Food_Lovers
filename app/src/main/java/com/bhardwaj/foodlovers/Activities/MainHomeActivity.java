package com.bhardwaj.foodlovers.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.bhardwaj.foodlovers.Fragments.CartFragment;
import com.bhardwaj.foodlovers.Fragments.HomeFragment;
import com.bhardwaj.foodlovers.Fragments.ProfileFragment;
import com.bhardwaj.foodlovers.Fragments.SearchFragment;
import com.bhardwaj.foodlovers.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.navigation_home) {
                    HomeFragment fragment = new HomeFragment();
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
