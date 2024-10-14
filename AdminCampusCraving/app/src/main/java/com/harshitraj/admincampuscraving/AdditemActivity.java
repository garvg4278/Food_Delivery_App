package com.harshitraj.admincampuscraving;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class AdditemActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView selectedImageView;
    private TextView selectImageTextView;
    private Button addItem;
    private EditText foodNameEditText;
    private EditText foodPriceEditText;
    private EditText shortDescriptionEditText;
    private EditText ingredientEditText;
    private DatabaseReference mDatabase;
    private Uri selectedImageUri; // Store selected image URI here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        selectedImageView = findViewById(R.id.selectedImage);
        selectImageTextView = findViewById(R.id.selectImage);
        addItem = findViewById(R.id.addItem);
        foodNameEditText = findViewById(R.id.foodName);
        foodPriceEditText = findViewById(R.id.foodPrice);
        shortDescriptionEditText = findViewById(R.id.addShortDesc);
        ingredientEditText = findViewById(R.id.addIngrediant);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        selectImageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch intent to pick image
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    private void addItem() {
        String foodName = foodNameEditText.getText().toString().trim();
        String foodPrice = foodPriceEditText.getText().toString().trim();
        String shortDescription = shortDescriptionEditText.getText().toString().trim();
        String ingredient = ingredientEditText.getText().toString().trim();

        if (foodName.isEmpty() || foodPrice.isEmpty() || shortDescription.isEmpty() || ingredient.isEmpty()) {
            Toast.makeText(AdditemActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate a unique key for the item
        String itemId = UUID.randomUUID().toString();

        if (selectedImageUri != null) {
            // Upload image to Firebase Storage
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            String imagePath = "images/" + UUID.randomUUID().toString() + ".jpg";
            StorageReference imageRef = storageRef.child(imagePath);
            imageRef.putFile(selectedImageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully, get download URL
                        imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageURL = uri.toString();

                            // Create AddMenu object
                            AddMenu addMenu = new AddMenu(foodName, foodPrice, imageURL, shortDescription, ingredient);

                            // Save AddMenu object to Firebase Realtime Database under "menu" node with itemId as key
                            mDatabase.child("menu").child(itemId).setValue(addMenu);

                            // Show success message
                            Toast.makeText(AdditemActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();

                            // Clear input fields
                            foodNameEditText.setText("");
                            foodPriceEditText.setText("");
                            shortDescriptionEditText.setText("");
                            ingredientEditText.setText("");
                        });
                    })
                    .addOnFailureListener(e -> {
                        // Handle error
                        Toast.makeText(AdditemActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        } else {
            // No image selected, create AddMenu object without imageURL
            AddMenu addMenu = new AddMenu(foodName, foodPrice, "", shortDescription, ingredient);

            // Save AddMenu object to Firebase Realtime Database under "menu" node with itemId as key
            mDatabase.child("menu").child(itemId).setValue(addMenu);

            // Show success message
            Toast.makeText(AdditemActivity.this, "Item added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            foodNameEditText.setText("");
            foodPriceEditText.setText("");
            shortDescriptionEditText.setText("");
            ingredientEditText.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request code is the same as what is passed here
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            // Get the Uri of the selected image
            selectedImageUri = data.getData();

            try {
                // Convert Uri to Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);

                // Set the selected image to the ImageView
                selectedImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}