/*
 *  AnalyticsConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics.di

import android.content.Context
import com.mealofjoy.android.analytics.AnalyticsManager
import com.mealofjoy.android.analytics.firebase.FirebaseAnalyticsWrapper
import com.mealofjoy.android.di.Component

interface AnalyticsComponent : Component {
    val analyticsManager: AnalyticsManager
}

class AnalyticsComponentImpl(ctx: Context) : AnalyticsComponent {
    override val analyticsManager: AnalyticsManager = AnalyticsManager(ctx).apply {
        addWrappers(FirebaseAnalyticsWrapper(context))
    }

}
