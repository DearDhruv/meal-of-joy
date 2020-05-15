/*
 *  DateExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import java.text.SimpleDateFormat
import java.util.*

val now: Date get() = Date()

fun Date.toISO8601(): String =
    this.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"))

fun Date.format(format: String, timeZone: TimeZone = TimeZone.getDefault()): String =
    SimpleDateFormat(format, Locale.ENGLISH).apply { setTimeZone(timeZone) }.format(this)
