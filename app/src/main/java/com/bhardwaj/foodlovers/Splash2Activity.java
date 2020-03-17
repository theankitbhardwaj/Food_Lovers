package com.bhardwaj.foodlovers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Splash2Activity extends AppCompatActivity {
    ImageView splashImg;
    ImageButton bt_next2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);
        splashImg = findViewById(R.id.fast_delivery_img);
        bt_next2 = findViewById(R.id.bt_next2);
    }

    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    public void next2(View view) {
        ActivityOptionsCompat activityOptionsCompatBT = ActivityOptionsCompat.makeSceneTransitionAnimation(this, bt_next2, ViewCompat.getTransitionName(bt_next2));
        ActivityOptionsCompat activityOptionsCompatIMG = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                splashImg,
                ViewCompat.getTransitionName(splashImg));
        Intent intent = new Intent(this, SplashFinalActivity.class);
        startActivity(intent, activityOptionsCompatIMG.toBundle());
    }


}
