/*
 *  CoreConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.di

import android.app.Application

interface CoreComponent : Component {
    val app: Application
}

class CoreComponentImpl(override val app: Application) : CoreComponent