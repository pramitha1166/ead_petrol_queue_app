package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LoginScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_screen);
    }
}