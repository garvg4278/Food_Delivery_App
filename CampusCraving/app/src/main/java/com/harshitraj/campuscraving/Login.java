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

public class Login extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    TextView donthave;
    Button forgetPassword;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        login = findViewById(R.id.loginbutton);
        donthave = findViewById(R.id.donthavebutton);
        forgetPassword =  findViewById(R.id.buttonForget);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, ForgetActivity.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMail = email.getText().toString();
                String userPassword = password.getText().toString();

                signInFirebase(userMail,userPassword);
            }
        });

        donthave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });
    }

    public void signInFirebase(String userMail, String userPassword){

        auth.signInWithEmailAndPassword(userMail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this,"Incorrect Email or Password",Toast.LENGTH_LONG);
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = auth.getCurrentUser();

        if(user != null){
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}