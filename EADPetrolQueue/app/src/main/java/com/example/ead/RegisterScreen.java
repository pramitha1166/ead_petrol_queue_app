/*
 * This is the login screen
 * Both shed owners and user can register from here
 * */
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.ead.services.DBHelper;
import com.example.ead.services.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RegisterScreen extends AppCompatActivity {

    Button register;
    Button toLogin;
    EditText password;
    EditText username;
    EditText confirmPassword;
    CheckBox isShedOwnerCheckBox;
    LoadingDialog loadingDialog = new LoadingDialog(RegisterScreen.this);
    DefaultMessage defaultMessage = new DefaultMessage(RegisterScreen.this);


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


        //navigate to login screen
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
                finish();
            }
        });


        //call when ontap register button and send registeration request
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDate()) {
                    loadingDialog.showLoadingDialog();

                    //payload
                    RequestParams requestParams = new RequestParams();
                    requestParams.put("username", usernameTxt);
                    requestParams.put("password", passwordTxt);
                    requestParams.put("confirm_password", confirmPasswordTxt);
                    requestParams.put("is_shed_owner", isShedOwner);


                    /*
                     * Make register request
                     * END POINT : /api/user/register
                     * Map : {username: ***, password: ***, confirm_password: ***, is_shed_owner: ***  }
                     * */
                    RestClient.post("/api/user/register", requestParams, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            //navigate to login if registration success
                            Intent intent = new Intent(RegisterScreen.this, RegisterScreen.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            defaultMessage.showDefaultDialog("Error with registering!");
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

    //validate registration data
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