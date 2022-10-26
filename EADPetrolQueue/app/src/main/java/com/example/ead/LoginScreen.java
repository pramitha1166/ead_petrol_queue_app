/*
 * This is the login screen
 * Both shed owners and user can log from here
 * */
package com.example.ead;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ead.services.DBHelper;
import com.example.ead.services.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


import org.json.JSONException;
import org.json.JSONObject;


import cz.msebera.android.httpclient.Header;
public class LoginScreen extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button login;
    Button toSignUp;
    LoadingDialog loadingDialog = new LoadingDialog(LoginScreen.this);
    DefaultMessage defaultMessage = new DefaultMessage(LoginScreen.this);
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        dbHelper = new DBHelper(this);

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

        //call when login button has been clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateDate()) {
                    System.out.println("Sending request...");
                    loadingDialog.showLoadingDialog();

                    //payload
                    RequestParams requestParams = new RequestParams();
                    requestParams.add("username", userName.getText().toString());
                    requestParams.add("password", password.getText().toString());

                    /*
                    * END POINT : /api/user/login
                    * Map : {username: ***, password: ***}
                    * */
                    RestClient.post("/api/user/login", requestParams, new AsyncHttpResponseHandler() {

                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            loadingDialog.dismiss();
                            System.out.println("Success => " + statusCode);
                            try {
                                JSONObject responseJson = new JSONObject(new String(responseBody));
                                System.out.println(responseJson.get("title"));

                                String userType = responseJson.getJSONObject("body").getString("user_type");
                                String username = responseJson.getJSONObject("body").getString("username");
                                String token = responseJson.getJSONObject("body").getString("token");

                                //save user data inside sqlite db
                                if(dbHelper.insetUser(username,token)) {

                                    //saved username in shared preferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", username);
                                    editor.apply();

                                    /*
                                     * If user type is shed owner,
                                     * please navigate to shed owner screen,
                                     * else navigate to user screen
                                     * */
                                    if(userType.equals("shed_owner")) {
                                        System.out.println("Shed owner");
                                        Intent intent = new Intent(LoginScreen.this, ShedOwnerScreen.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        System.out.println("User");
                                        Intent intent = new Intent(LoginScreen.this, UserScreen.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }else {
                                    defaultMessage.showDefaultDialog("Error with saving user data!");
                                }


                            } catch (Exception e) {
                                defaultMessage.showDefaultDialog("Something went wrong!");
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            try {
                                JSONObject responseJson = new JSONObject(new String(responseBody));
                                //show login error message
                                defaultMessage.showDefaultDialog(responseJson.getJSONObject("body").getString("message"));

                            } catch (JSONException e) {
                                defaultMessage.showDefaultDialog("Something went wrong!");
                                e.printStackTrace();
                            }

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

    //validate login data
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