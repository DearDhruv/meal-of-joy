/*
 *  DemoAnalytics.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.analytics

import java.util.*


interface DemoAnalytics : Analytics {

    fun successfulAppStartedOn(date: Date)

}