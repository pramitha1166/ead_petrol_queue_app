/*
* This is user dashboard here user can view available petrol or diesel amount
* Also user can see available vehicles in the queue
* Also here user able update arrival time and leave time
* */
package com.example.ead;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ead.services.DBHelper;
import com.example.ead.services.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserScreen extends AppCompatActivity {

    TextView queueLength;
    TextView petrolCount;
    TextView dieselCount;
    DatePicker arrivalDate;
    TimePicker arrivalTime;
    DatePicker leaveDate;
    TimePicker leaveTime;
    EditText feedback;
    Button updateArrival;
    Button updateLeave;
    DBHelper dbHelper;
    LoadingDialog loadingDialog = new LoadingDialog(UserScreen.this);
    DefaultMessage defaultMessage = new DefaultMessage(UserScreen.this);

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        dbHelper = new DBHelper(this);

        queueLength = findViewById(R.id.queue_length);
        petrolCount = findViewById(R.id.available_petrol);
        dieselCount = findViewById(R.id.available_diesel);
        arrivalDate = findViewById(R.id.arrival_date);
        arrivalTime = findViewById(R.id.arrival_time);
        leaveDate = findViewById(R.id.leaving_date);
        leaveTime = findViewById(R.id.leaving_time);
        updateArrival = findViewById(R.id.update_arrival);
        updateLeave = findViewById(R.id.update_leave);
        feedback = findViewById(R.id.feedback);

        String arrDate = arrivalDate.getDayOfMonth() + "-" + arrivalDate.getMonth() + "-" + arrivalDate.getYear();
        String arrTime = arrivalTime.getHour() + "-" + arrivalTime.getMinute();

        String leaveDateText = leaveDate.getDayOfMonth() + "-" + leaveDate.getMonth() + "-" + leaveDate.getYear();
        String leaveTimeText = leaveTime.getHour() + "-" + leaveTime.getMinute();


        //get username from shred preferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");


        //get token from sqlite database
        String token = dbHelper.getToken(username);

        RequestParams requestParams = new RequestParams();
        requestParams.put("token", token);

        /*
         * Realtime listener end point for listen queue length update.
         * This will update queue in real time when db updated
         * END POINT : /api/shed/viewqueulegnth
         * Map : {token: ***}
         * */
        RestClient.listen("/api/user/viewqueulegnth/" + username, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject responseJson = null;
                try {
                    responseJson = new JSONObject(new String(responseBody));
                    String count = responseJson.getJSONObject("body").getString("count");
                    queueLength.setText(count);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        /*
         * Realtime listener end point for listen available petrol amount update.
         * This will update petrol amount in real time when db updated
         * END POINT : /api/shed/petrol_count
         * Map : {token: ***}
         * */
        RestClient.listen("/api/user/petrol_count/" + username, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject responseJson = null;
                try {
                    responseJson = new JSONObject(new String(responseBody));
                    String count = responseJson.getJSONObject("body").getString("count");
                    petrolCount.setText(count);

                } catch (JSONException e) {
                    defaultMessage.showDefaultDialog("Something went wrong!");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                defaultMessage.showDefaultDialog("Something went wrong!");
            }
        });

        /*
         * Realtime listener end point for listen available diesel amount update.
         * This will update diesel amount in real time when db updated
         * END POINT : /api/shed/diesel_count
         * Map : {token: ***}
         * */
        RestClient.listen("/api/user/diesel_count/" + username, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JSONObject responseJson = null;
                try {
                    responseJson = new JSONObject(new String(responseBody));
                    String count = responseJson.getJSONObject("body").getString("count");
                    dieselCount.setText(count);

                } catch (JSONException e) {
                    defaultMessage.showDefaultDialog("Something went wrong!");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                defaultMessage.showDefaultDialog("Something went wrong!");
            }
        });


        /*
         * End point for update user arrival time update
         * END POINT : /api/user/arrival/
         * Map : {token: ***, arrDate: ***, arrTime: ***}
         * */
        updateArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.showLoadingDialog();

                //payload
                RequestParams requestParams = new RequestParams();
                requestParams.put("arrTime", arrTime);
                requestParams.put("arrDate", arrDate);
                requestParams.put("token", token);


                RestClient.post("/api/user/arrival/", requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Updated Successfully!");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        defaultMessage.showDefaultDialog("Something went wrong!");
                        loadingDialog.dismiss();
                    }
                });
            }
        });

        /*
         * End point for update user leave time update
         * END POINT : /api/user/arrival/
         * Map : {token: ***, leaveDate: ***, leaveTime: ***}
         * */
        updateLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.showLoadingDialog();

                //payload
                RequestParams requestParams = new RequestParams();
                requestParams.put("leaveDate", leaveTimeText);
                requestParams.put("leaveDate", leaveDateText);
                requestParams.put("token", token);


                RestClient.post("/api/user/leave/", requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Updated Successfully!");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        defaultMessage.showDefaultDialog("Something went wrong!");
                        loadingDialog.dismiss();
                    }
                });
            }
        });

    }
}