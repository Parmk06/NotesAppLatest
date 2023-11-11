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
import android.widget.TextView;
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
    private Button btnNoAccount;
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
        btnNoAccount = findViewById(R.id.btnNoAccount);

//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                loginUser();
//            }
//        });

        //////

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password;
                email = String.valueOf(ed_email.getText());
                password = String.valueOf(ed_password.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        btnNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRegisterUser();
            }
        });
    }

    private void OpenRegisterUser() {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }
}

//    private void loginUser() {
//        progressBar.setVisibility(View.VISIBLE);
//        String email = ed_email.getText().toString().trim();
//        String password = ed_password.getText().toString().trim();
//
//        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
//            showError("Enter both email and password");
//            return;
//        }
//
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressBar.setVisibility(View.GONE);
//                        if (task.isSuccessful()) {
//                            showToast("Login Successful");
//                            navigateToWelcome();
//                        } else {
//                            handleLoginFailure(task);
//                        }
//                    }
//                });
//    }
//
//    private void handleLoginFailure(Task<AuthResult> task) {
//        if (task.getException().getMessage().contains("no user record")) {
//            showToast("Account not registered. Sign up first.");
//            // Uncomment the following lines if you want to navigate to the Register activity
//            // navigateToRegister();
//        } else {
//            showToast("Authentication failed.");
//        }
//    }
//
//    private void showError(String message) {
//        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//        progressBar.setVisibility(View.GONE);
//    }
//
//    private void showToast(String message) {
//        Toast.makeText(Login.this, message, Toast.LENGTH_SHORT).show();
//    }
//
//    private void navigateToWelcome() {
//        Intent intent = new Intent(getApplicationContext(), Welcome.class);
//        startActivity(intent);
//        finish();
//    }
//
//
//}