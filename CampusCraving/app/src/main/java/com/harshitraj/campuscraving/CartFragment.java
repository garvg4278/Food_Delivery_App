package com.harshitraj.campuscraving;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private ArrayList<String> foodNameList = new ArrayList<>();
    private ArrayList<String> foodPriceList = new ArrayList<>();
    private ArrayList<Integer> foodImageList = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize data
        initFoodData();

        // Create and set adapter
        adapter = new CartAdapter(foodNameList, foodPriceList, foodImageList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initFoodData() {
        foodNameList.add("Burger");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("pasta");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("pasta");

        foodPriceList.add("₹25");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");

        foodImageList.add(R.drawable.menu1);
        foodImageList.add(R.drawable.menu2);
        foodImageList.add(R.drawable.menu3);
        foodImageList.add(R.drawable.menu4);
        foodImageList.add(R.drawable.menu5);
        foodImageList.add(R.drawable.menu6);
        foodImageList.add(R.drawable.menu7);
    }
}