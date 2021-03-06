package com.bhardwaj.foodlovers.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bhardwaj.foodlovers.Activities.FoodDetailsActivity;
import com.bhardwaj.foodlovers.Adapters.CartAdapter;
import com.bhardwaj.foodlovers.Models.ModelCart;
import com.bhardwaj.foodlovers.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    static List<ModelCart> cartItems = new ArrayList<>();
    static CartAdapter adapter;
    static TextView txtSubtotal, txtTax, txtTotal, txtEmpty, txtH;
    static Button placeOrder, empty;
    static RecyclerView rvCart;
    static ImageView imgSad;
    static ConstraintLayout linearLayoutSubtotal, linearLayoutTotal, layoutMain;
    static LinearLayout parentCart;
    public static void addData(String imgUrl, String name, String desc, String price) {
        boolean exists = false;
        int i;
        for (i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getfName().equals(name)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            updateAddData(i, imgUrl, name, desc, price, cartItems.get(i).getfCount());
            total();
        } else {
            cartItems.add(new ModelCart(name, desc, 1, price, imgUrl));
            total();
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        buttonSet();
        emptySet();
    }

    public CartFragment() {
        // Required empty public constructor
    }

    public static void updateAddData(int position, String imgUrl, String name, String desc, String price, int count) {
        count += 1;
        cartItems.set(position, new ModelCart(name, desc, count, price, imgUrl));
        adapter.notifyItemChanged(position);
        total();
    }

    public static void removeData(int position, String imgUrl, String name, String desc, String price, int count) {
        count -= 1;
        if (count > 0) {
            cartItems.set(position, new ModelCart(name, desc, count, price, imgUrl));
            adapter.notifyItemChanged(position);
        }
        if (count == 0) {
            cartItems.remove(position);
            adapter.notifyItemRemoved(position);
        }
        buttonSet();
        emptySet();
        total();
    }

    static public void total() {
        int subtotal = 0;
        double tax = 0;
        for (ModelCart m : cartItems) {
            subtotal += (m.getfCount() * Integer.parseInt(m.getfPrice()));
            tax = 0.13 * subtotal;
        }
        double total = subtotal + tax;
        if (txtSubtotal != null && txtSubtotal != null && txtTotal != null) {
            txtSubtotal.setText("₹ " + subtotal);
            txtTax.setText("₹ " + String.format("%.0f", tax));
            txtTotal.setText("₹ " + String.format("%.0f", total));
        }
    }

    static void buttonSet() {
        if (placeOrder != null && empty != null) {
            if (cartItems.isEmpty()) {
                placeOrder.setEnabled(false);
                empty.setEnabled(false);
                empty.setVisibility(View.INVISIBLE);
            } else {
                empty.setVisibility(View.VISIBLE);
                empty.setEnabled(true);
                placeOrder.setEnabled(true);
            }
        }
    }

    static void emptySet() {
        if (cartItems.isEmpty()) {
            rvCart.setVisibility(View.INVISIBLE);
            rvCart.setEnabled(false);
            parentCart.removeAllViews();
            parentCart.addView(txtH);
            //parentCart.removeView(rvCart);
            parentCart.addView(imgSad);
            parentCart.addView(txtEmpty);
            parentCart.removeView(layoutMain);
            //parentCart.removeView(linearLayoutSubtotal);
            //parentCart.removeView(linearLayoutTotal);
        } else {
            if (rvCart != null) {
                rvCart.setEnabled(true);
                rvCart.setVisibility(View.VISIBLE);
                parentCart.removeAllViews();
                parentCart.addView(txtH);
                //parentCart.addView(rvCart);
                parentCart.addView(layoutMain);
                //parentCart.addView(linearLayoutSubtotal);
                //parentCart.addView(linearLayoutTotal);
                parentCart.removeView(imgSad);
                parentCart.removeView(txtEmpty);
            }
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCart = view.findViewById(R.id.rv_cartItems);
        txtSubtotal = view.findViewById(R.id.txt_subtotal);
        txtH = view.findViewById(R.id.txt_cartH);
        txtTotal = view.findViewById(R.id.txt_total);
        txtTax = view.findViewById(R.id.txt_tax);
        placeOrder = view.findViewById(R.id.btn_CartPlaceOrder);
        layoutMain = view.findViewById(R.id.layoutMain);
        empty = view.findViewById(R.id.btn_CartEmpty);
        imgSad = view.findViewById(R.id.img_sad);
        txtEmpty = view.findViewById(R.id.txt_EmptyCart);
        linearLayoutSubtotal = view.findViewById(R.id.linearLayout_subtotal);
        linearLayoutTotal = view.findViewById(R.id.linearLayout_total);
        parentCart = view.findViewById(R.id.layoutParentCart);
        parentCart.removeAllViews();
        emptySet();
        total();
        buttonSet();
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItems.clear();
                adapter.notifyDataSetChanged();
                total();
                buttonSet();
                emptySet();
                Toast.makeText(getContext(), "Order placed.", Toast.LENGTH_SHORT).show();
            }
        });
        empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItems.clear();
                adapter.notifyDataSetChanged();
                total();
                buttonSet();
                emptySet();
                Toast.makeText(getContext(), "Cart emptied.", Toast.LENGTH_SHORT).show();
            }
        });

        if (cartItems != null) {
            adapter = new CartAdapter(getContext(), cartItems);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            rvCart.setLayoutManager(layoutManager);
            rvCart.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);

    }

}
