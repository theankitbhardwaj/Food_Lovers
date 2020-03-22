package com.bhardwaj.foodlovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Splash1Activity extends AppCompatActivity {
    ImageView splashImg;
    SharedPreferenceConfig preferenceConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);
        splashImg = findViewById(R.id.search_splash_img);
        if (preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, MainHomeActivity.class));
        }
    }

    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    public void next1(View view) {
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                splashImg,
                ViewCompat.getTransitionName(splashImg));
        Intent intent = new Intent(this, Splash2Activity.class);
        startActivity(intent, activityOptionsCompat.toBundle());
    }


}
