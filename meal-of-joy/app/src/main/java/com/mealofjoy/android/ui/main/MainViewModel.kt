/*
 *  MainViewModel.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11/05/2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.ui.main

import androidx.lifecycle.ViewModel
import com.mealofjoy.android.analytics.DemoAnalytics
import com.mealofjoy.android.extensions.now

class MainViewModel private constructor(private val analytics: DemoAnalytics) : ViewModel() {
    // TODO: dhruv create stateful model

    fun logAppOpened() = analytics.successfulAppStartedOn(now)

    companion object {
        fun create(analytics: DemoAnalytics): MainViewModel = MainViewModel(analytics)
    }
}