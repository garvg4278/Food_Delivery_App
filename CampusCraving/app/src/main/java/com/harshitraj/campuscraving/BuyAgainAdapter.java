// BuyAgainAdapter.java
package com.harshitraj.campuscraving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BuyAgainAdapter extends RecyclerView.Adapter<BuyAgainAdapter.ViewHolder> {

    private List<String> foodNameList;
    private List<String> foodPriceList;
    private List<Integer> foodImageList;

    public BuyAgainAdapter(List<String> foodNameList, List<String> foodPriceList, List<Integer> foodImageList) {
        this.foodNameList = foodNameList;
        this.foodPriceList = foodPriceList;
        this.foodImageList = foodImageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_again_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.foodNameTextView.setText(foodNameList.get(position));
        holder.priceTextView.setText(foodPriceList.get(position));
        holder.foodImageView.setImageResource(foodImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView;
        TextView priceTextView;
        ImageView foodImageView;
        CardView buyAgainCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            foodImageView = itemView.findViewById(R.id.foodImageView);
            buyAgainCardView = itemView.findViewById(R.id.buyAgainCardView);
        }
    }
}