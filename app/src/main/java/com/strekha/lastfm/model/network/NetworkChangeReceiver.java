package com.strekha.lastfm.model.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.strekha.lastfm.LastFmApplication;

public class NetworkChangeReceiver{


    public static boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager =
                (ConnectivityManager) LastFmApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) return false;
        NetworkInfo network = connectivityManager.getActiveNetworkInfo();
        return network != null && network.isConnected();
    }

}
