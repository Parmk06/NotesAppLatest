package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    private Button btnLogin;
    private Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Find buttons by ID
        btnLogin = findViewById(R.id.btn_login);
        btnGetStarted = findViewById(R.id.btn_get_started);

        // Set click listeners
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
    }

    // Open the Login activity
    private void openLoginActivity() {
        Intent intent = new Intent(Welcome.this, Login.class);
        startActivity(intent);
    }

    // Open the Register activity
    private void openRegisterActivity() {
        Intent intent = new Intent(Welcome.this, Register.class);
        startActivity(intent);
    }
}
