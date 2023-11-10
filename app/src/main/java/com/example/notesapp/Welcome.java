package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().setTitle("Welcome page");
    }

    public void openLoginPage(View view)
    {
        startActivity(new Intent(this,Login.class));
    }
    public void openRegisterPage(View view){

        startActivity(new Intent(this,Register.class));
    }
}