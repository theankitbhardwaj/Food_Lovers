package com.bhardwaj.foodlovers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

public class SplashFinalActivity extends AppCompatActivity {
    AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.5F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_final);
    }

    public void getStarted(View view) {
        view.startAnimation(buttonClick);
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

}
