package com.bhardwaj.foodlovers.Activities;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.MenuItem;

import com.bhardwaj.foodlovers.Fragments.CartFragment;
import com.bhardwaj.foodlovers.Fragments.HomeFragment;
import com.bhardwaj.foodlovers.Fragments.ProfileFragment;
import com.bhardwaj.foodlovers.Fragments.SearchFragment;
import com.bhardwaj.foodlovers.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainHomeActivity extends AppCompatActivity {
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    CartFragment cartFragment = new CartFragment();
    ProfileFragment profileFragment = new ProfileFragment();
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
                    displayHome();
                    return true;
                } else if (id == R.id.navigation_search) {
                    displaySearch();
                    return true;
                } else if (id == R.id.navigation_cart) {
                    displayCart();
                    return true;
                } else if (id == R.id.navigation_profile) {
                    displayProfile();
                    return true;
                } else {
                    return false;
                }

            }
        });

        bottomNav.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void displayHome() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (homeFragment.isAdded()) {
            ft.show(homeFragment);
        } else {
            ft.add(R.id.mainFragment, homeFragment);
        }
        if (searchFragment.isAdded()) {
            ft.hide(searchFragment);
        }
        if (cartFragment.isAdded()) {
            ft.hide(cartFragment);
        }
        if (profileFragment.isAdded()) {
            ft.hide(profileFragment);
        }
        ft.commit();
    }

    private void displaySearch() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (searchFragment.isAdded()) {
            ft.show(searchFragment);
        } else {
            ft.add(R.id.mainFragment, searchFragment);
        }
        if (homeFragment.isAdded()) {
            ft.hide(homeFragment);
        }
        if (cartFragment.isAdded()) {
            ft.hide(cartFragment);
        }
        if (profileFragment.isAdded()) {
            ft.hide(profileFragment);
        }
        ft.commit();
    }

    private void displayCart() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (cartFragment.isAdded()) {
            ft.show(cartFragment);
        } else {
            ft.add(R.id.mainFragment, cartFragment);
        }
        if (searchFragment.isAdded()) {
            ft.hide(searchFragment);
        }
        if (homeFragment.isAdded()) {
            ft.hide(homeFragment);
        }
        if (profileFragment.isAdded()) {
            ft.hide(profileFragment);
        }
        ft.commit();
    }

    private void displayProfile() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (profileFragment.isAdded()) {
            ft.show(profileFragment);
        } else {
            ft.add(R.id.mainFragment, profileFragment);
        }
        if (searchFragment.isAdded()) {
            ft.hide(searchFragment);
        }
        if (cartFragment.isAdded()) {
            ft.hide(cartFragment);
        }
        if (homeFragment.isAdded()) {
            ft.hide(homeFragment);
        }
        ft.commit();
    }
}
