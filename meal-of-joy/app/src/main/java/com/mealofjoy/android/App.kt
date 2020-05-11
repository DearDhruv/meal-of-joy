/*
 *  App.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.app.Application
import com.mealofjoy.android.analytics.di.AnalyticsComponentImpl
import com.mealofjoy.android.di.ComponentRouter

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createComponentRouter()
    }

    private fun createComponentRouter() {
        // initialize component router for DIY DI
        ComponentRouter.init(this) {
            // analytics component
            inject("analytics", AnalyticsComponentImpl(applicationContext))
        }
    }

    // todo: create module for notification
}