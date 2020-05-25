/*
 *  MainActivity.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11/05/2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mealofjoy.android.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}