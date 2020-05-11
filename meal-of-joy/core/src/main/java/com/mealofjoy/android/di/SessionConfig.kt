/*
 *  SessionConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.di

interface SessionComponent : Component {
}

class SessionComponentImpl(private val coreComponent: CoreComponent) :
    SessionComponent {
    init {
        // init config for image loader, etc.
    }

}


