package com.bhardwaj.foodlovers.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhardwaj.foodlovers.Fragments.CartFragment;
import com.bhardwaj.foodlovers.Models.ModelCart;
import com.bhardwaj.foodlovers.R;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    int count;
    int i = 0;
    ModelCart m;
    private Context context;
    private List<ModelCart> cartItems;

    public CartAdapter(Context context, List<ModelCart> cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context));
        builder.build().load(cartItems.get(position).getfImage()).placeholder(R.drawable.load).fit().into(holder.fImage);
        holder.fName.setText(cartItems.get(position).getfName());
        holder.fDesc.setText(cartItems.get(position).getfDesc());
        count = cartItems.get(position).getfCount();
        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment.updateAddData(holder.getAdapterPosition(), cartItems.get(holder.getAdapterPosition()).getfImage(), cartItems.get(holder.getAdapterPosition()).getfName(), cartItems.get(holder.getAdapterPosition()).getfDesc(), cartItems.get(holder.getAdapterPosition()).getfPrice(), cartItems.get(holder.getAdapterPosition()).getfCount());
            }
        });
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartFragment.removeData(holder.getAdapterPosition(), cartItems.get(holder.getAdapterPosition()).getfImage(), cartItems.get(holder.getAdapterPosition()).getfName(), cartItems.get(holder.getAdapterPosition()).getfDesc(), cartItems.get(holder.getAdapterPosition()).getfPrice(), cartItems.get(holder.getAdapterPosition()).getfCount());
            }
        });
        holder.fCount.setText(String.valueOf(cartItems.get(holder.getAdapterPosition()).getfCount()));
        holder.fPrice.setText("₹ " + cartItems.get(position).getfPrice());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fImage;
        TextView fName, fDesc, fCount, fPrice;
        Button btnAdd, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fImage = itemView.findViewById(R.id.img_foodCart);
            fName = itemView.findViewById(R.id.txt_nameCart);
            fDesc = itemView.findViewById(R.id.txt_descCart);
            fCount = itemView.findViewById(R.id.txt_countCart);
            fPrice = itemView.findViewById(R.id.txt_cartPrice);
            btnAdd = itemView.findViewById(R.id.btn_addItem);
            btnRemove = itemView.findViewById(R.id.btn_removeItem);
        }
    }
}
