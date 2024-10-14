package com.harshitraj.campuscraving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText Name;
    EditText Password;
    EditText Email;
    Button createAccount;
    TextView alreadyhave;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Name = findViewById(R.id.editTextName);
        Password = findViewById(R.id.editTextPassword2);
        Email = findViewById(R.id.editTextEmail2);
        createAccount = findViewById(R.id.buttonSignup);
        alreadyhave=findViewById(R.id.alreadyhavebutton);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = Name.getText().toString();
                String userPassword = Password.getText().toString();
                String userEmail = Email.getText().toString();


                signUpFirebase(userName,userEmail,userPassword);
            }
        });

        alreadyhave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }

    public void signUpFirebase(String userName, String userEmail, String userPassword){
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Signup.this, "Your Account Has Been Created", Toast.LENGTH_LONG).show();

                            // Save user data to Firebase Realtime Database
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userId;
                            userId = ((FirebaseUser) firebaseUser).getUid();
                            User user = new User(userName, userEmail);
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            databaseReference.child(userId).setValue(user);

                            Intent intent = new Intent(Signup.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Signup.this, "There is a problem", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}