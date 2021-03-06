package com.example.earthquakes.framework.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectivityMonitor @Inject constructor(private val appContext: Context) {

    fun isOnline(): Boolean {
        val connMgr = appContext.applicationContext
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}