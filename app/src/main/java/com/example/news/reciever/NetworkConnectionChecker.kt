package com.example.news.reciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager


class NetworkConnectionChecker : BroadcastReceiver() {


    override fun onReceive(context: Context, intent: Intent) {
        try {
            val status = isOnline(context)
            connectivityReceiverListener.onNetworkConnectionChanged(status)
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    /**
     * @return status of network connection : Boolean
     * */
    companion object {
        lateinit var connectivityReceiverListener: ConnectivityReceiverListener
        fun isOnline(context: Context): Boolean {
            return try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val netInfo = cm.activeNetworkInfo
                //should check null because in airplane mode it will be null
                netInfo != null && netInfo.isConnected
            } catch (e: NullPointerException) {
                e.printStackTrace()
                false
            }
        }
    }

    interface ConnectivityReceiverListener {
        fun onNetworkConnectionChanged(status: Boolean)
    }
}