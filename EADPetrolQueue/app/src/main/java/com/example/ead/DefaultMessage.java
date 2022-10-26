package com.example.ead;

import android.app.AlertDialog;
import android.content.Context;

public class DefaultMessage {

    AlertDialog alertDialog;

    void showDefaultDialog(String message, Context context) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setCancelable(true);
        alert.setMessage(message);
        alert.setPositiveButton()
    }

}
