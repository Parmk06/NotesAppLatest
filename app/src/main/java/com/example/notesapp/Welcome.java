package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {
    TextView tv_welcome;
    Button btn_login, btn_get_started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn_login = findViewById(R.id.btn_login);
        btn_get_started = findViewById(R.id.btn_get_started);
       

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the login_activity
                Intent intent = new Intent(Welcome.this, Login.class);
                startActivity(intent);
            }
        });
    }


}