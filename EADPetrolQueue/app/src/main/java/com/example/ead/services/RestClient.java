/*
 * Common service for calling rest services
 * Using static method post, the app is able to make api request for each services
 * */
package com.example.ead.services;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class RestClient {

    //The base url where app backend is hosted
    private static final String BASE_URL = "http://192.168.43.60:3000";
    private static AsyncHttpClient client = new AsyncHttpClient();

    //static method post call
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        System.out.println("Calling endpoint: "+getAbsoluteUrl(url));
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    //static method get call
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        System.out.println("Calling endpoint: "+getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    //static method listen realtime db update
    public static void listen(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        System.out.println("Calling endpoint: "+getAbsoluteUrl(url));
        client.listen(getAbsoluteUrl(url), params, responseHandler);
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
