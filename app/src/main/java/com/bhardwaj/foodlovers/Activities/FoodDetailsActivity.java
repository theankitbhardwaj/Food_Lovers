package com.bhardwaj.foodlovers.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.foodlovers.Adapters.CartAdapter;
import com.bhardwaj.foodlovers.Fragments.CartFragment;
import com.bhardwaj.foodlovers.Models.ModelBreakfast;
import com.bhardwaj.foodlovers.Models.ModelCart;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        final String imgUrl = getIntent().getStringExtra("FoodImage");
        final String name = getIntent().getStringExtra("FoodName");
        final String price = getIntent().getStringExtra("FoodPrice");
        String rating = getIntent().getStringExtra("FoodRating");
        final String desc = getIntent().getStringExtra("FoodDesc");
        String quantity = getIntent().getStringExtra("FoodQuantity");
        ImageView imgFood = findViewById(R.id.img_fdImage);
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this));
        builder.build().load(imgUrl).fit().centerCrop().into(imgFood);
        TextView txtName = findViewById(R.id.txt_foodName);
        TextView txtfdName = findViewById(R.id.txt_fdName);
        TextView txtfdRating = findViewById(R.id.txt_fdRating);
        TextView txtfdPrice = findViewById(R.id.txt_fdPrice);
        TextView txtfdQuantity = findViewById(R.id.txt_fdQuantity);
        TextView txtfdDesc = findViewById(R.id.txt_fdDesc);
        Button btnAdd = findViewById(R.id.btn_addToCart);
        txtName.setText(name);
        txtfdName.setText(name);
        txtfdRating.setText(rating);
        txtfdPrice.setText("• ₹ " + price);
        txtfdDesc.setText(desc);
        txtfdQuantity.setText(Html.fromHtml(quantity));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment.addData(imgUrl, name, desc, price);
                Toast.makeText(getApplicationContext(), "Added to cart.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
