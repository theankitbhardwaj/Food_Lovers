package com.bhardwaj.foodlovers.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhardwaj.foodlovers.Models.ModelTopPicks;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TopPickAdapter extends RecyclerView.Adapter<TopPickAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<ModelTopPicks> models;
    private Context context;

    public TopPickAdapter(List<ModelTopPicks> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.toppicks, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context));
        builder.build().load(models.get(position).getDishImage()).into(holder.imageView);
        holder.textView_rating.setText(models.get(position).getDishRating());
        holder.textView_price.setText("• ₹ " + models.get(position).getDishPrice());
        holder.textView_name.setText(models.get(position).getDishName());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_rating, textView_price, textView_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_tpFood);
            textView_rating = itemView.findViewById(R.id.textView_tpRating);
            textView_price = itemView.findViewById(R.id.textView_tpPrice);
            textView_name = itemView.findViewById(R.id.textView_tpFood);
        }
    }
}
