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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminProfileActivity extends AppCompatActivity {
    ImageView backButton;
    EditText adminName, adminAddress, adminMail, adminPhone, adminRestaurant;
    boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backButton = findViewById(R.id.backButton);
        adminName = findViewById(R.id.adminName);
        adminAddress = findViewById(R.id.adminAddress);
        adminMail = findViewById(R.id.adminMail);
        adminPhone = findViewById(R.id.adminPhone);
        adminRestaurant = findViewById(R.id.adminRestaurant);

        // Disable editing initially
        setEditTextsEnabled(false);

        findViewById(R.id.editYourProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle editing
                isEditing = !isEditing;
                setEditTextsEnabled(isEditing);
            }
        });

        findViewById(R.id.saveInfoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save information to Firebase
                saveInformationToFirebase();

                // Disable editing after saving
                isEditing = false;
                setEditTextsEnabled(false);

                // Show toast message
                Toast.makeText(AdminProfileActivity.this, "Profile information saved successfully", Toast.LENGTH_SHORT).show();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setEditTextsEnabled(boolean enabled) {
        adminName.setEnabled(enabled);
        adminAddress.setEnabled(enabled);
        adminMail.setEnabled(enabled);
        adminPhone.setEnabled(enabled);
        adminRestaurant.setEnabled(enabled);
    }

    private void saveInformationToFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference adminProfileRef = database.getReference("AdminProfile");

        String name = adminName.getText().toString().trim();
        String address = adminAddress.getText().toString().trim();
        String email = adminMail.getText().toString().trim();
        String phone = adminPhone.getText().toString().trim();
        String restaurant = adminRestaurant.getText().toString().trim();

        String adminId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        adminProfileRef.child(adminId).child("Name").setValue(name);
        adminProfileRef.child(adminId).child("Address").setValue(address);
        adminProfileRef.child(adminId).child("Email").setValue(email);
        adminProfileRef.child(adminId).child("Phone").setValue(phone);
        adminProfileRef.child(adminId).child("Restaurant").setValue(restaurant);
    }

}