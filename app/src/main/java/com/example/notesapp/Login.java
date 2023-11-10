package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.notesapp.Register;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.lang.ref.WeakReference;

public class Login extends AppCompatActivity {

    private EditText ed_email, ed_password;
    private Button btn_login;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        progressBar.setVisibility(View.VISIBLE);
        String email = ed_email.getText().toString().trim();
        String password = ed_password.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            showError("Enter both email and password");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            showToast("Login Successful");
                            navigateToWelcome();
                        } else {
                            handleLoginFailure(task);
                        }
                    }
                });
    }

    private void handleLoginFailure(Task<AuthResult> task) {
        if (task.getException().getMessage().contains("no user record")) {
            showToast("Account not registered. Sign up first.");
            // Uncomment the following lines if you want to navigate to the Register activity
            // navigateToRegister();
        } else {
            showToast("Authentication failed.");
        }
    }

    private void showError(String message) {
        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    private void showToast(String message) {
        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
    }

    private void navigateToWelcome() {
        Intent intent = new Intent(getApplicationContext(), Welcome.class);
        startActivity(intent);
        finish();
    }


}