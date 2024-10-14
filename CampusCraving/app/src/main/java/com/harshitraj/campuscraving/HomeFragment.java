package com.harshitraj.campuscraving;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView viewMenu;
    private RecyclerView recyclerView;
    private PopularAdapter adapter;
    private ArrayList<String> foodNameList = new ArrayList<>();
    private ArrayList<String> foodPriceList = new ArrayList<>();
    private ArrayList<Integer> foodImageList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewMenu = view.findViewById(R.id.viewAllMenu);
        viewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the activity you want to navigate to
                Intent intent = new Intent(getActivity(), BottomNavigationItemView.class);
                startActivity(intent);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize data
        initFoodData();

        // Create and set adapter
        adapter = new PopularAdapter(foodNameList, foodPriceList, foodImageList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initFoodData() {
        foodNameList.add("Burger");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("pasta");
        foodNameList.add("Meggi");
        foodNameList.add("Noodles");
        foodNameList.add("Fries");

        foodPriceList.add("₹25");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");
        foodPriceList.add("₹55");

        foodImageList.add(R.drawable.menu1);
        foodImageList.add(R.drawable.menu2);
        foodImageList.add(R.drawable.menu3);
        foodImageList.add(R.drawable.menu4);
        foodImageList.add(R.drawable.menu5);
        foodImageList.add(R.drawable.menu6);
        foodImageList.add(R.drawable.menu7);
    }
}