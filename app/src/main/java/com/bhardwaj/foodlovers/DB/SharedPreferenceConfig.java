package com.bhardwaj.foodlovers.DB;

import android.content.Context;
import android.content.SharedPreferences;

import com.bhardwaj.foodlovers.R;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }

    public void writeUserAddress(String address) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.address), address);
        editor.commit();
    }

    public String readUserAddress() {
        String data = sharedPreferences.getString(context.getResources().getString(R.string.address), "Enter Address Here");
        return data;
    }

    public void writeOnboardingStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.onboarding_status), status);
        editor.commit();
    }

    public boolean readOnboardingStatus() {
        boolean status;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.onboarding_status), false);
        return status;
    }


}

