package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class RegisterScreen extends AppCompatActivity {

    Button register;
    Button toLogin;
    EditText password;
    EditText username;
    EditText confirmPassword;
    CheckBox isShedOwnerCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        register = findViewById(R.id.register);
        toLogin = findViewById(R.id.to_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);
        isShedOwnerCheckBox = findViewById(R.id.is_shed_owner);

        boolean isShedOwner = isShedOwnerCheckBox.isChecked();
        String passwordTxt = password.getText().toString();
        String confirmPasswordTxt = confirmPassword.getText().toString();
        String usernameTxt = username.getText().toString();




        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });



    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean validateDate() {
        boolean status = true;
        if(isEmpty(username)) {
            username.setError("Username is required!");
            status = false;
        }
        if(isEmpty(password)) {
            password.setError("Password is required!");
            status = false;
        }
        if(isEmpty(confirmPassword)) {
            confirmPassword.setError("Confirm Password is required!");
            status = false;
        }

        return status;
    }
}