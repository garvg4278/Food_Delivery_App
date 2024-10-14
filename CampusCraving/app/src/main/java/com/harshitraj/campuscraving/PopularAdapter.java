package com.harshitraj.campuscraving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MenuViewHolder> {

    final ArrayList<String> foodNameList;
    final ArrayList<String> foodPriceList;
    final ArrayList<Integer> foodImageList;

    public PopularAdapter(ArrayList<String> foodNameList, ArrayList<String> foodPriceList, ArrayList<Integer> foodImageList) {
        this.foodNameList = foodNameList;
        this.foodPriceList = foodPriceList;
        this.foodImageList = foodImageList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        // Set initial values
        holder.textFoodName.setText(foodNameList.get(position));
        holder.textFoodPrice.setText(foodPriceList.get(position));
        holder.imageView.setImageResource(foodImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private TextView textFoodName, textFoodPrice;
        private ImageView imageView;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            textFoodName = itemView.findViewById(R.id.foodNameTextView);
            textFoodPrice = itemView.findViewById(R.id.priceTextView);
            imageView = itemView.findViewById(R.id.foodImageView);
        }
    }
}