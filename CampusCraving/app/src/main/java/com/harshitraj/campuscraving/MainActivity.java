package com.harshitraj.campuscraving;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    Button signOut;
    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    CartFragment cartFragment = new CartFragment();
    SearchFragment searchFragment = new SearchFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();
                }else if (menuItem.getItemId()==R.id.cart){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,cartFragment).commit();
                }else if (menuItem.getItemId()==R.id.search){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,searchFragment).commit();
                }else if (menuItem.getItemId()==R.id.history){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,historyFragment).commit();
                }else if (menuItem.getItemId()==R.id.profile){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,profileFragment).commit();
                }
                return true;
            }
        });

        signOut = findViewById(R.id.button);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}