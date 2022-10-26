/*
 * This is common dialog box for entire app
 * */
package com.example.ead;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DefaultMessage {

    AlertDialog alertDialog;
    Activity activity;

    DefaultMessage(Activity activity) {
        this.activity = activity;
    }

    //show dialog message
    void showDefaultDialog(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setCancelable(true);
        alert.setMessage(message);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        alertDialog = alert.create();
        alertDialog.show();
    }


}
