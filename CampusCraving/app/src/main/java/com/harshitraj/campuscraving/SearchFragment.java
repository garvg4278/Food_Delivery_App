package com.harshitraj.campuscraving;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private SearchView searchEditText;
    private RecyclerView searchResultsRecyclerView;

    private ArrayList<String> foodNameList; // Change List<String> to ArrayList<String>
    private ArrayList<String> foodPriceList; // Change List<String> to ArrayList<String>
    private ArrayList<Integer> foodImageList; // Change List<Integer> to ArrayList<Integer>

    private PopularAdapter searchResultsAdapter;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize views
        searchEditText = view.findViewById(R.id.search_edit_text); // Corrected the type to SearchView
        searchResultsRecyclerView = view.findViewById(R.id.search_results_recycler_view);

        // Initialize lists
        foodNameList = new ArrayList<>();
        foodNameList.add("Burger");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("Pasta");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("Pasta");

        foodPriceList = new ArrayList<>();
        foodPriceList.add("₹25");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");

        foodImageList = new ArrayList<>();
        foodImageList.add(R.drawable.menu1);
        foodImageList.add(R.drawable.menu2);
        foodImageList.add(R.drawable.menu3);
        foodImageList.add(R.drawable.menu4);
        foodImageList.add(R.drawable.menu5);
        foodImageList.add(R.drawable.menu6);
        foodImageList.add(R.drawable.menu7);

        // Initialize adapter
        searchResultsAdapter = new PopularAdapter((ArrayList<String>) foodNameList, (ArrayList<String>) foodPriceList, (ArrayList<Integer>) foodImageList);

        // Set adapter to RecyclerView
        searchResultsRecyclerView.setAdapter(searchResultsAdapter);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Add text change listener to SearchView for search functionality
        searchEditText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFood(newText);
                return true;
            }
        });

        return view;
    }


    private void searchFood(String query) {
        ArrayList<String> filteredFoodNameList = new ArrayList<>(); // Change List<String> to ArrayList<String>
        ArrayList<String> filteredFoodPriceList = new ArrayList<>(); // Change List<String> to ArrayList<String>
        ArrayList<Integer> filteredFoodImageList = new ArrayList<>(); // Change List<Integer> to ArrayList<Integer>

        for (int i = 0; i < foodNameList.size(); i++) {
            if (foodNameList.get(i).toLowerCase().contains(query.toLowerCase())) {
                filteredFoodNameList.add(foodNameList.get(i));
                filteredFoodPriceList.add(foodPriceList.get(i));
                filteredFoodImageList.add(foodImageList.get(i));
            }
        }

        // Update data of the existing adapter
        searchResultsAdapter.foodNameList.clear();
        searchResultsAdapter.foodNameList.addAll(filteredFoodNameList);
        searchResultsAdapter.foodPriceList.clear();
        searchResultsAdapter.foodPriceList.addAll(filteredFoodPriceList);
        searchResultsAdapter.foodImageList.clear();
        searchResultsAdapter.foodImageList.addAll(filteredFoodImageList);

        // Notify adapter about the data change
        searchResultsAdapter.notifyDataSetChanged();
    }
}