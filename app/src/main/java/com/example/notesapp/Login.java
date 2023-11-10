package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import java.lang.ref.WeakReference;

public class Login extends AppCompatActivity {

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable launchWelcomeRunnable = new LaunchWelcomeRunnable(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler.postDelayed(launchWelcomeRunnable, 1000);
    }

    private static class LaunchWelcomeRunnable implements Runnable {
        private final WeakReference<Login> loginActivityReference;

        LaunchWelcomeRunnable(Login loginActivity) {
            this.loginActivityReference = new WeakReference<>(loginActivity);
        }

        @Override
        public void run() {
            Login loginActivity = loginActivityReference.get();
            if (loginActivity != null) {
                Intent intent = new Intent(loginActivity, Welcome.class);
                loginActivity.startActivity(intent);
                loginActivity.finish();
            }
        }
    }
}

