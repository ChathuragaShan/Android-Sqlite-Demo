package com.shan.chathuranga.ormlite.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;

import com.shan.chathuranga.ormlite.R;


/**
 * Created by ChathurangaShan on 2/14/2017.
 */

public class NetworkStatus {

    private Context context;

    public NetworkStatus(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enable Internet");
        builder.setMessage("Application required internet to function")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*Intent callGPSSettingIntent = new Intent(
                                android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        context.startActivity(callGPSSettingIntent);*/
                    }
                });
        final AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        ContextCompat.getColor(context, R.color.positiveButtonNotSelected));
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(
                        ContextCompat.getColor(context, R.color.negativeButtonNotSelected));
            }
        });
        alert.show();
    }
}
