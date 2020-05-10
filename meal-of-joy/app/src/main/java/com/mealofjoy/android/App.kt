/*
 *  App.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.iid.FirebaseInstanceId

class App : Application() {

    private val mFirebaseAnalytics: FirebaseAnalytics by lazy {
        FirebaseAnalytics.getInstance(this)
    }

    override fun onCreate() {
        super.onCreate()
        logAppStarted()
        // notification()
    }

    private fun logAppStarted() {
        // todo: dhruv refactor to module
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, getString(R.string.app_name))
        bundle.putString(FirebaseAnalytics.Param.CONTENT, "App started")
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        // throw NullPointerException("test java NullPointerException")
    }

    private fun notification() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
//                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
//                val msg = getString(R.string.msg_token_fmt, token)
//                Log.d(TAG, msg)
                Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
            })
    }
}