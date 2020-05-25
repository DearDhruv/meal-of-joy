/*
 *  Wrapper.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics

interface Wrapper {
    companion object {
        const val WRAPPER_FIREBASE = "wrapper_firebase"
        // add any other wrapper
    }

    val initialized: Boolean
    val wrapperType: String?

    fun wrapEvent(analyticsEvent: AnalyticsEvent)

}