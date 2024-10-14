package com.harshitraj.campuscraving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MenuViewHolder>{

    private final ArrayList<String> foodNameList;
    private final ArrayList<String>foodPriceList;
    private final ArrayList<Integer>foodImageList;
    private ArrayList<Integer> quantityList;

    public CartAdapter(ArrayList<String> foodNameList, ArrayList<String> foodPriceList, ArrayList<Integer> foodImageList) {
        this.foodNameList = foodNameList;
        this.foodPriceList = foodPriceList;
        this.foodImageList = foodImageList;

        quantityList = new ArrayList<>();
        for (int i = 0; i < foodNameList.size(); i++) {
            quantityList.add(1);
        }

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);

        return new MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        // Set initial values
        holder.textFoodName.setText(foodNameList.get(position));
        holder.textFoodPrice.setText(foodPriceList.get(position));
        holder.imageView.setImageResource(foodImageList.get(position));
        holder.quantityTextView.setText(String.valueOf(quantityList.get(position)));

        // Plus button click listener
        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = quantityList.get(position);
                if (quantity < 10) { // Limit quantity to 10
                    quantity++;
                    quantityList.set(position, quantity);
                    holder.quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });

        // Minus button click listener
        holder.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = quantityList.get(position);
                if (quantity > 1) { // Ensure quantity doesn't go below 1
                    quantity--;
                    quantityList.set(position, quantity);
                    holder.quantityTextView.setText(String.valueOf(quantity));
                }
            }
        });

        // Delete button click listener
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodNameList.remove(position);
                foodPriceList.remove(position);
                foodImageList.remove(position);
                quantityList.remove(position);
                notifyDataSetChanged(); // Refresh RecyclerView
            }
        });
    }


    @Override
    public int getItemCount() {
        return foodNameList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView textFoodName, textFoodPrice, quantityTextView;
        private ImageView imageView, plusButton, minusButton, deleteButton;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            textFoodName = itemView.findViewById(R.id.foodNameTextView);
            textFoodPrice = itemView.findViewById(R.id.priceTextView);
            imageView = itemView.findViewById(R.id.foodImageView);
            plusButton = itemView.findViewById(R.id.plusButton);
            minusButton = itemView.findViewById(R.id.minusButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
        }
    }

}
