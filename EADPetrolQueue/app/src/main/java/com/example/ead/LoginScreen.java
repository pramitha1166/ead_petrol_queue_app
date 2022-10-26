package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ead.services.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;


import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;

public class LoginScreen extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button login;
    Button toSignUp;
    LoadingDialog loadingDialog = new LoadingDialog(LoginScreen.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        userName = findViewById(R.id.username);
        password = findViewById(R.id.password);
        toSignUp = findViewById(R.id.to_register);
        login = findViewById(R.id.login);

        toSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDate()) {
                    System.out.println("Sending request...");
                    loadingDialog.showLoadingDialog();
                    RestClient.post("/api/user/login", null, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            loadingDialog.dismiss();
                            System.out.println("Success => " + statusCode);
                            try {
                                JSONObject responseJson = new JSONObject(new String(responseBody));
                                System.out.println(responseJson.get("title"));

                                String userType = responseJson.getJSONObject("body").getString("user_type");

                                if(userType.equals("shed_owner")) {
                                    System.out.println("Shed owner");
                                }


                            } catch (Exception e) {

                            }


                            //redirect to driver dashboard if user is driver
                            //else redirect to shed owner dashboard

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                        }
                    });
                }


            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean validateDate() {
        boolean status = true;
        if(isEmpty(userName)) {
            userName.setError("Username is required!");
            status = false;
        }
        if(isEmpty(password)) {
            password.setError("Password is required!");
            status = false;
        }
        return status;
    }
}