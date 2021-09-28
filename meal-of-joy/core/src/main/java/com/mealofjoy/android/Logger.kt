/*
 *  Logger.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.util.Log
import com.mealofjoy.android.di.BuildConfig

object Logger {
    private val TAG by lazy { Logger::class.java.simpleName }

    fun e(Msg: String) = log(Log.ERROR, TAG, Msg)

    fun e(Tag: String, Msg: String) = log(Log.ERROR, Tag, Msg)

    fun i(Msg: String) = log(Log.INFO, TAG, Msg)

    fun i(Tag: String, Msg: String) = log(Log.INFO, Tag, Msg)

    fun d(Msg: String) = log(Log.DEBUG, TAG, Msg)

    fun d(Tag: String, Msg: String) = log(Log.DEBUG, Tag, Msg)

    fun v(Msg: String) = log(Log.VERBOSE, TAG, Msg)

    fun v(Tag: String, Msg: String) = log(Log.VERBOSE, Tag, Msg)

    fun w(Msg: String) = log(Log.WARN, TAG, Msg)

    fun w(Tag: String, Msg: String) = log(Log.WARN, Tag, Msg)

    private fun log(LEVEL: Int, Tag: String?, Message: String) {
        if (BuildConfig.DEBUG) Log.println(LEVEL, Tag ?: TAG, Message)
    }
}
