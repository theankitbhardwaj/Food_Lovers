package com.bhardwaj.foodlovers.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhardwaj.foodlovers.Activities.FoodDetailsActivity;
import com.bhardwaj.foodlovers.Models.ModelFood;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    List<ModelFood> food;
    int p;

    public SearchAdapter(Context context, List<ModelFood> food) {
        this.context = context;
        this.food = food;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.breakfast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (holder.imageView != null && holder.textView_rating != null && holder.textView_price != null && holder.textView_name != null) {
            Picasso.Builder builder = new Picasso.Builder(context);
            builder.downloader(new OkHttpDownloader(context));
            builder.build().load(food.get(position).getDishImage()).placeholder(R.drawable.load).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, FoodDetailsActivity.class);
                    i.putExtra("FoodImage", food.get(position).getDishImage());
                    i.putExtra("FoodName", food.get(position).getDishName());
                    i.putExtra("FoodPrice", food.get(position).getDishPrice());
                    i.putExtra("FoodRating", food.get(position).getDishRating());
                    i.putExtra("FoodDesc", food.get(position).getDishDesc());
                    i.putExtra("FoodQuantity", food.get(position).getDishQuantity());
                    context.startActivity(i);
                }
            });
            holder.textView_rating.setText(food.get(position).getDishRating());
            holder.textView_price.setText("• ₹ " + food.get(position).getDishPrice());
            holder.textView_name.setText(food.get(position).getDishName());
        }

    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_rating, textView_price, textView_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_bfFood);
            textView_rating = itemView.findViewById(R.id.textView_bfRating);
            textView_price = itemView.findViewById(R.id.textView_bfPrice);
            textView_name = itemView.findViewById(R.id.textView_bfFood);
        }
    }
}
