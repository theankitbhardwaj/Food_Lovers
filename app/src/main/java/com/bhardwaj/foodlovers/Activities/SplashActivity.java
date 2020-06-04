package com.bhardwaj.foodlovers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bhardwaj.foodlovers.Adapters.SplashAdapter;
import com.bhardwaj.foodlovers.R;
import com.bhardwaj.foodlovers.DB.SharedPreferenceConfig;

public class SplashActivity extends AppCompatActivity {
    SharedPreferenceConfig preferenceConfig;
    private TextView[] mDots;
    private LinearLayout layout;
    private int currentPage;
    private RelativeLayout pLayout;
    private Button btnNext,btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        layout = findViewById(R.id.pagination);
        pLayout = findViewById(R.id.splashLayout);
        btnNext = findViewById(R.id.btnNext);
        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(),SignInActivity.class);
                startActivity(i);
            }
        });
        if (preferenceConfig.readLoginStatus()) {
            startActivity(new Intent(this, MainHomeActivity.class));
        }
        final ViewPager2 vp = findViewById(R.id.viewPager2);
        SplashAdapter adapter = new SplashAdapter();
        vp.setAdapter(adapter);
        addDots(0);
        vp.registerOnPageChangeCallback(new OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                if(currentPage == 2){
                    btnNext.setEnabled(false);
                    btnNext.setVisibility(View.INVISIBLE);
                    pLayout.removeView(layout);
                    btnFinish.setEnabled(true);
                    btnFinish.setVisibility(View.VISIBLE);
                }else{
                    addDots(position);
                    try{
                        pLayout.addView(layout);
                    }catch (Exception e){

                    }finally {
                        btnFinish.setVisibility(View.INVISIBLE);
                        btnFinish.setEnabled(false);
                        btnNext.setEnabled(true);
                        btnNext.setVisibility(View.VISIBLE);
                    }

                }
                super.onPageSelected(position);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(currentPage+1,true);
            }
        });
    }

    void addDots(int position){
        mDots = new TextView[3];
        layout.removeAllViews();
        for(int i=0;i<3;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.orangeFade));
            layout.addView(mDots[i]);
        }
        if(mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.orangeDark));
        }
    }

}
