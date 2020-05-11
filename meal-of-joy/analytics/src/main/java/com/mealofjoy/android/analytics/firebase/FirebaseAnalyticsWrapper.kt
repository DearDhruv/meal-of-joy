/*
 *  FirebaseAnalyticsWrapper.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import com.google.firebase.analytics.FirebaseAnalytics
import com.mealofjoy.android.analytics.AnalyticsEvent
import com.mealofjoy.android.analytics.Wrapper
import com.mealofjoy.android.analytics.Wrapper.Companion.WRAPPER_FIREBASE

@SuppressLint("MissingPermission")
class FirebaseAnalyticsWrapper(context: Context) : Wrapper {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    init {
        if (!initialized) {
            firebaseAnalytics = FirebaseAnalytics.getInstance(context)
        }
    }

    override val initialized: Boolean
        get() = ::firebaseAnalytics.isInitialized

    override val wrapperType: String?
        get() = WRAPPER_FIREBASE


    override fun wrapEvent(analyticsEvent: AnalyticsEvent) {
        if (initialized) {
            logEvent(analyticsEvent.eventName, analyticsEvent.bundle)
        }
    }

    private fun logEvent(event: String, @Nullable bundle: Bundle?) {
        if (initialized) {
            firebaseAnalytics.logEvent(event, bundle)
        }
    }
}