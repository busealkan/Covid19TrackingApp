package com.busealkan.covid19.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo;

object NetworkUtil {
    fun isThereInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return if (networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected) {
            true
        } else {
            false
        }
    }
}