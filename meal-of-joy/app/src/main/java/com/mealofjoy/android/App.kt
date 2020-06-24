/*
 *  App.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.app.Application
import android.content.Intent
import com.mealofjoy.android.analytics.di.AnalyticsComponentImpl
import com.mealofjoy.android.di.ComponentRouter
import com.mealofjoy.android.di.SessionComponentImpl
import com.mealofjoy.android.di.component
import com.mealofjoy.android.network.InternetCheckService
import com.mealofjoy.android.twitter.di.TwitterComponentImpl

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createComponentRouter()

        startService(Intent(applicationContext, InternetCheckService::class.java))
    }

    private fun createComponentRouter() {
        // initialize component router for DIY DI
        ComponentRouter.init(this) {
            inject("session", SessionComponentImpl("core".component()))
            // analytics component
            inject("analytics", AnalyticsComponentImpl("core".component()))
            inject("twitter", TwitterComponentImpl("core".component()))
        }

    }

}