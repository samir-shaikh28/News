package com.example.news.app

import android.app.Application
import com.example.news.reciever.NetworkConnectionChecker

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

    companion object {
        private lateinit var mInstance: App
        @Synchronized
        fun getInstance(): App {
            return mInstance
        }
    }

    fun setConnectivityListener(listener: NetworkConnectionChecker.ConnectivityReceiverListener) {
        NetworkConnectionChecker.connectivityReceiverListener = listener
    }
}