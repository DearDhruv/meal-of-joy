/*
 *  APPPreferences.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.preferences

import android.annotation.SuppressLint
import android.content.Context
import androidx.preference.PreferenceManager
import com.mealofjoy.android.Logger

open class APPPreferences(appContext: Context) {

    private val TAG = "APPPreferences"

    private val preferences = PreferenceManager.getDefaultSharedPreferences(appContext)

    protected fun deleteObject(key: String) {
        val editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }

    // Save data on same thread.
    @SuppressLint("ApplySharedPref")
    protected fun saveString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.commit()
        Logger.e(TAG, "str- $value")
    }

    // Save data in background thread.
    protected fun saveStringAsync(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
        Logger.e(TAG, "str- $value")
    }

    protected fun getString(key: String): String? {
        return preferences.getString(key, null) ?: return null
    }

    protected fun saveBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
        Logger.e(TAG, "bool- $value")
    }

    protected fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

}
