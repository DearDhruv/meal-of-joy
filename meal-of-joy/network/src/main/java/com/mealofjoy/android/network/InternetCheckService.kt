/*
 *  InternetCheckService.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 25-06-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.network

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.widget.Toast


class InternetCheckService : Service() {

    // Binder given to clients
    private val binder = InternetCheckBinder()

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    inner class InternetCheckBinder : Binder() {
        // Return this instance of InternetCheckService so clients can call public methods
        fun getService(): InternetCheckService = this@InternetCheckService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        setInternetCallback()
        return START_STICKY
    }

    fun setInternetCallback() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        cm?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.registerDefaultNetworkCallback(
                    // NetworkRequest.Builder().build(),
                    object : ConnectivityManager.NetworkCallback() {
                        override fun onAvailable(network: Network) {
                            QuickTaskIntentService.startActionSearch(
                                this@InternetCheckService,
                                "DearDhruv"
                            )
                        }

                        override fun onLost(network: Network?) {
                            QuickTaskIntentService.startActionLog(
                                this@InternetCheckService,
                                "Internet Lost"
                            )
                        }
                    })
            }
        }
    }
}