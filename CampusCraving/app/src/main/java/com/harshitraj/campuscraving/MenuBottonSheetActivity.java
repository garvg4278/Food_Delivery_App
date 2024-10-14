package com.harshitraj.campuscraving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuBottonSheetActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MenuBottomSheetAdapter adapter;
    ImageView backButton;
    private ArrayList<String> foodNameList = new ArrayList<>();
    private ArrayList<String>foodPriceList = new ArrayList<>();
    private ArrayList<Integer>foodImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu_botton_sheet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MenuBottonSheetActivity.this));
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuBottonSheetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        foodNameList.add("Burger");
        foodNameList.add("Sandwich");
        foodNameList.add("Item");
        foodNameList.add("pasta");

        foodPriceList.add("₹25");
        foodPriceList.add("₹50");
        foodPriceList.add("₹55");
        foodPriceList.add("₹70");

        foodImageList.add(R.drawable.menu1);
        foodImageList.add(R.drawable.menu2);
        foodImageList.add(R.drawable.menu3);
        foodImageList.add(R.drawable.menu4);

        adapter = new MenuBottomSheetAdapter(foodNameList,foodPriceList,foodImageList);
        recyclerView.setAdapter(adapter);

    }
}