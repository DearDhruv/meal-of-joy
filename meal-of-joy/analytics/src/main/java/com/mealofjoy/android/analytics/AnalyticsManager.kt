/*
 *  AnalyticsManager.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics

import android.content.Context

open class AnalyticsManager(val context: Context) {

    private val wrappers = ArrayList<Wrapper>()

    fun addWrapper(wrapper: Wrapper) {
        if (!wrappers.contains(wrapper)) {
            wrappers.add(wrapper)
        }
    }

    fun addWrappers(vararg wrappers: Wrapper) {
        wrappers.forEach { w -> addWrapper(w) }
    }

    fun removeWrapper(wrapperDescription: String) {
        wrappers.removeAll { it.wrapperType == wrapperDescription }
    }

    fun logEvent(analyticsEvent: AnalyticsEvent) {
        // Send event to wrappers
        wrapAndLogEvent(analyticsEvent)
    }

    private fun wrapAndLogEvent(analyticsEvent: AnalyticsEvent) {
        wrappers.forEach { wrapper -> wrapper.wrapEvent(analyticsEvent) }
    }

}