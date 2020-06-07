package com.bhardwaj.foodlovers.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhardwaj.foodlovers.Models.ModelBreakfast;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        String imgUrl = getIntent().getStringExtra("FoodImage");
        String name = getIntent().getStringExtra("FoodName");
        String price = getIntent().getStringExtra("FoodPrice");
        String rating = getIntent().getStringExtra("FoodRating");
        String desc = getIntent().getStringExtra("FoodDesc");
        String quantity = getIntent().getStringExtra("FoodQuantity");
        ImageView imgFood = findViewById(R.id.img_fdImage);
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this));
        builder.build().load(imgUrl).centerCrop().resize(1000, 1000).into(imgFood);
        TextView txtName = findViewById(R.id.txt_foodName);
        TextView txtfdName = findViewById(R.id.txt_fdName);
        TextView txtfdRating = findViewById(R.id.txt_fdRating);
        TextView txtfdPrice = findViewById(R.id.txt_fdPrice);
        TextView txtfdQuantity = findViewById(R.id.txt_fdQuantity);
        TextView txtfdDesc = findViewById(R.id.txt_fdDesc);
        txtName.setText(name);
        txtfdName.setText(name);
        txtfdRating.setText(rating);
        txtfdPrice.setText("• ₹ " + price);
        txtfdDesc.setText(desc);
        txtfdQuantity.setText(Html.fromHtml(quantity));
    }
}
