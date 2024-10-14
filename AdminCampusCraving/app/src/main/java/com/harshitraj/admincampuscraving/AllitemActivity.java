package com.harshitraj.admincampuscraving;

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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllitemActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AllitemAdapter adapter;
    ImageView backButton;
    private ArrayList<AddMenu> menuList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_allitem);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(AllitemActivity.this));
        backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllitemActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Initialize Firebase Database reference
        DatabaseReference menuRef = FirebaseDatabase.getInstance().getReference().child("menu");

        // Add listener to retrieve menu items from Firebase Database
        menuRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                menuList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    AddMenu menu = snapshot.getValue(AddMenu.class);
                    menuList.add(menu);
                }
                // Set up the adapter with the retrieved menu items
                adapter = new AllitemAdapter(menuList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}