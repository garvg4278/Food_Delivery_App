package com.harshitraj.admincampuscraving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewUserActivity extends AppCompatActivity {
    ImageView back;
    EditText editTextName, editTextEmail, editTextPassword, restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_new_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.backButton);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword2);
        restaurantName = findViewById(R.id.restaurantName);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateNewUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.createNewUserButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    private void createUser() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String restaurant = restaurantName.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            editTextName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (restaurant.isEmpty()) {
            restaurantName.setError("Restaurant name is required");
            restaurantName.requestFocus();
            return;
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if (user != null) {
                            String userId = user.getUid();
                            saveUserToDatabase(userId, name, email, restaurant);
                        }
                    } else {
                        Toast.makeText(CreateNewUserActivity.this, "Failed to create user", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserToDatabase(String userId, String name, String email, String restaurant) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("AdminUsers");
        DatabaseReference userRef = usersRef.child(userId);

        userRef.child("Name").setValue(name);
        userRef.child("Email").setValue(email);
        userRef.child("Restaurant").setValue(restaurant);

        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}