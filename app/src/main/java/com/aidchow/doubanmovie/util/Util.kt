package com.aidchow.doubanmovie.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.aidchow.doubanmovie.App

/**
 * Created by aidchow on 17-5-26.
 */

object Util {
    fun isOnle(): Boolean {
        val connMgr: ConnectivityManager = App.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netWorkInfo: NetworkInfo? = connMgr.activeNetworkInfo
        return netWorkInfo != null && netWorkInfo.isConnected
    }
}