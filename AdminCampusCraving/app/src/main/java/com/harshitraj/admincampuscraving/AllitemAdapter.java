package com.harshitraj.admincampuscraving;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllitemAdapter extends RecyclerView.Adapter<AllitemAdapter.MenuViewHolder> {

    private final ArrayList<AddMenu> menuList;

    public AllitemAdapter(ArrayList<AddMenu> menuList) {
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        if (menuList != null && !menuList.isEmpty()) {
            AddMenu menu = menuList.get(position);
            holder.textFoodName.setText(menu.getFoodName());
            holder.textFoodPrice.setText(menu.getFoodPrice());
            // Set image URL using Glide library
//            Glide.with(holder.itemView.getContext()).load(menu.getImageURL()).into(holder.imageView);
        } else {
            // Handle the case when menuList is null or empty
            holder.textFoodName.setText("");
            holder.textFoodPrice.setText("");
            // Set a placeholder image or hide the image view
            // holder.imageView.setImageResource(R.drawable.placeholder_image);
        }
    }



    @Override
    public int getItemCount() {
        return menuList.size();
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