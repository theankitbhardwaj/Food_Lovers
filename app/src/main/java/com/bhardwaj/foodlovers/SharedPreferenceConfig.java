package com.bhardwaj.foodlovers;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferenceConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.login_preference), Context.MODE_PRIVATE);
    }

    public void writeUserData(String username, String mob, String email) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.login_username), username);
        editor.putString(context.getResources().getString(R.string.login_mob), mob);
        editor.putString(context.getResources().getString(R.string.login_email), email);
        editor.commit();
    }

    public String[] readUserData() {
        String[] data = new String[3];
        data[0] = sharedPreferences.getString(context.getResources().getString(R.string.login_username), "Your Name");
        data[1] = sharedPreferences.getString(context.getResources().getString(R.string.login_mob), "Your Mob no.");
        data[2] = sharedPreferences.getString(context.getResources().getString(R.string.login_email), "Your Email");
        return data;
    }

    public void writeLoginStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.login_status_preference), status);
        editor.commit();
    }

    public boolean readLoginStatus() {
        boolean status;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.login_status_preference), false);
        return status;
    }
}

