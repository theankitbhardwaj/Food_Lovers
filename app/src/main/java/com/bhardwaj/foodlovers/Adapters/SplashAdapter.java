package com.bhardwaj.foodlovers.Adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhardwaj.foodlovers.R;

public class SplashAdapter extends RecyclerView.Adapter<SplashAdapter.SplashItemHolder> {
    String desc[] = {"Search and Order delicious food and enjoy with your friends and family.","Get it fast with your Food Lovers account. Order online from top Food restaurants in your locality.","Easy payment options like cash, card and online payment."};
    int img[] = {R.drawable.search_image_01,R.drawable.scootr_img_02,R.drawable.wallet_img_03};
    String headline[] = {"Search Delicious Food","Fastest Delivery","Easy Payment Options"};
    @NonNull
    @Override
    public SplashItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.splash_slide,parent,false);
        return new SplashItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SplashItemHolder holder, int position) {
        holder.bind(headline[position],img[position],desc[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public class SplashItemHolder extends RecyclerView.ViewHolder {
        public SplashItemHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(String headline,int img,String desc){
            TextView txtHead = itemView.findViewById(R.id.txt_headline);
            ImageView imgSplash = itemView.findViewById(R.id.img_splash);
            TextView txtDesc = itemView.findViewById(R.id.txt_desc);
            txtHead.setText(headline);
            imgSplash.setImageResource(img);
            txtDesc.setText(desc);
        }
    }

}
