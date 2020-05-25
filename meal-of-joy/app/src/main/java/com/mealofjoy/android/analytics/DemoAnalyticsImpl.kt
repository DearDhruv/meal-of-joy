/*
 *  DemoAnalyticsImpl.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics

import android.os.Bundle
import com.mealofjoy.android.extensions.toISO8601
import java.util.*


class DemoAnalyticsImpl(private val analytics: AnalyticsManager) : DemoAnalytics {

    override fun successfulAppStartedOn(date: Date) {
        val bundle = Bundle()
        bundle.putString(AnalyticsConstants.KEY_PARAM_START_TIME, date.toISO8601())
        analytics.logEvent(AnalyticsEvent(AnalyticsConstants.KEY_EVENT_APP_START, bundle))
    }

}