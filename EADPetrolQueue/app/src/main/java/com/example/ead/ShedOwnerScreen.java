/*
* This is shed owner dashboard here shed owner can update petrol and diesel arrival time and finish time
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
import android.widget.TimePicker;

import com.example.ead.services.DBHelper;
import com.example.ead.services.RestClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class ShedOwnerScreen extends AppCompatActivity {

    Button updateArrival;
    Button updateFinish;
    TimePicker arrivalTime;
    DatePicker arrivalDate;
    TimePicker finishTime;
    DatePicker finishDate;
    EditText typeArrival;
    EditText typeFinish;
    DBHelper dbHelper;

    LoadingDialog loadingDialog = new LoadingDialog(ShedOwnerScreen.this);
    DefaultMessage defaultMessage = new DefaultMessage(ShedOwnerScreen.this);


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_owner_screen);

        dbHelper = new DBHelper(this);

        updateArrival = findViewById(R.id.update_arrival);
        updateFinish = findViewById(R.id.update_finish);
        arrivalDate = findViewById(R.id.arrival_date);
        arrivalTime = findViewById(R.id.arrival_time);
        finishDate = findViewById(R.id.finish_date);
        finishTime = findViewById(R.id.finish_time);
        typeArrival = findViewById(R.id.fuel_type_arrival);
        typeFinish = findViewById(R.id.fuel_type_finish);

        String arrDate = arrivalDate.getDayOfMonth() + "-" + arrivalDate.getMonth() + "-" + arrivalDate.getYear();
        String arrTime = arrivalTime.getHour() + "-" + arrivalTime.getMinute();

        String finDate = finishDate.getDayOfMonth() + "-" + finishDate.getMonth() + "-" + finishDate.getYear();
        String finTime = finishTime.getHour() + "-" + finishTime.getMinute();

        String typeArrivalText = typeArrival.getText().toString();
        String typeFinishText = typeFinish.getText().toString();


        //method for update fuel arrival time and date
        updateArrival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog.showLoadingDialog();
                //get username from shred preferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");


                //get token from sqlite database
                String token = dbHelper.getToken(username);


                RequestParams requestParams = new RequestParams();
                requestParams.put("token", token);
                requestParams.put("arrDate", arrDate);
                requestParams.put("arrTime", arrTime);
                requestParams.put("type", typeArrivalText);


                /*
                 * End point for fuel update arrival time update
                 * END POINT : /api/shed/addarrivaltime
                 * Map : {token: ***, arrDate: ***, arrTime: ***, type: ***}
                 * */
                RestClient.post("/api/shed/addarrivaltime", requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Successfully updated!");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Something went wrong!");
                    }
                });

            }
        });

        //method for update fuel finish time and date
        updateFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog.showLoadingDialog();

                //get username from shred preferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");


                //get token from sqlite database
                String token = dbHelper.getToken(username);


                RequestParams requestParams = new RequestParams();
                requestParams.put("token", token);
                requestParams.put("finDate", finDate);
                requestParams.put("finTime", finTime);
                requestParams.put("type", typeFinishText);


                /*
                 * End point for update fuel finish time update
                 * END POINT : /api/shed/addfinishtime
                 * Map : {token: ***, arrDate: ***, arrTime: ***, type: ***}
                 * */
                RestClient.post("/api/shed/addfinishtime", requestParams, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Successfully updated!");
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        loadingDialog.dismiss();
                        defaultMessage.showDefaultDialog("Something went wrong!");
                    }
                });

            }
        });

    }
}