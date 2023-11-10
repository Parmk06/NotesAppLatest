package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText ed_email, ed_password;
    private Button btn_signup;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_signup = findViewById(R.id.btn_signup);
        progressBar = findViewById(R.id.progressBar);

        btn_signup.setOnClickListener(v -> signup());
    }

    private void signup() {
        String email = ed_email.getText().toString().trim();
        String password = ed_password.getText().toString().trim();

        if (!validateData(email, password)) {
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            Log.d("RegisterActivity", "createUserWithEmail:success");
                            Toast.makeText(Register.this, "Account created successfully.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this, Welcome.class));
                            finish();
                        } else {
                            Log.w("RegisterActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private boolean validateData(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            ed_email.setError("Enter email");
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_email.setError("Invalid email");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            ed_password.setError("Enter password");
            return false;
        }

        if (password.length() < 6) {
            ed_password.setError("Password must be at least 6 characters");
            return false;
        }

        return true;
    }
}