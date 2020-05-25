/*
 *  AnalyticsConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics.di

import com.mealofjoy.android.analytics.AnalyticsManager
import com.mealofjoy.android.analytics.firebase.FirebaseAnalyticsWrapper
import com.mealofjoy.android.di.Component
import com.mealofjoy.android.di.CoreComponent

interface AnalyticsComponent : Component {
    val analyticsManager: AnalyticsManager
}

class AnalyticsComponentImpl(
    coreComponent: CoreComponent
) : AnalyticsComponent {
    override val analyticsManager: AnalyticsManager =
        AnalyticsManager(coreComponent.app.applicationContext).apply {
            addWrappers(FirebaseAnalyticsWrapper(context))
        }

}
