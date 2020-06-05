package com.bhardwaj.foodlovers.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhardwaj.foodlovers.Models.ModelOffers;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<ModelOffers> models;
    private Context context;

    public OfferAdapter(List<ModelOffers> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.offers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context));
        final ImageView img = new ImageView(context);
        builder.build().load(models.get(position).getDishImage()).into(img, new Callback() {
            @Override
            public void onSuccess() {
                holder.imageView.setBackground(img.getDrawable());
            }

            @Override
            public void onError() {

            }
        });
        holder.textView_offer.setText(models.get(position).getOffer());
        holder.textView_offerCondition.setText(models.get(position).getOfferCondition());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView_offer, textView_offerCondition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView_offDishImg);
            textView_offer = itemView.findViewById(R.id.textView_offer);
            textView_offerCondition = itemView.findViewById(R.id.textView_offCondition);
        }
    }
}
