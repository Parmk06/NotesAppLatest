package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class Register extends AppCompatActivity {

    EditText ed_email, ed_password;
    Button btn_signup;
    ImageView email_icon, image_login;
    //private Patterns Pattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_signup = findViewById(R.id.btn_signup);
        email_icon = findViewById(R.id.email_icon);
        image_login = findViewById(R.id.image_login);

        btn_signup.setOnClickListener(v-> signUp());


    }

    private void signUp() {
        String email = ed_email.getText().toString();
        String password = ed_password.getText().toString();

        boolean isValid = validateData(email,password);
    }
    boolean validateData(String email, String password){

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            ed_email.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            ed_password.setError("Password length is invalid! ");
            return false;

        }
        return true;
    }
}