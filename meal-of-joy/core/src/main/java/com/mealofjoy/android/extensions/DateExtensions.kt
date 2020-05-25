/*
 *  DateExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import android.annotation.SuppressLint
import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

val now: Date get() = Date()

fun Date.toISO8601(): String =
    this.format("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", TimeZone.getTimeZone("UTC"))


fun Date.format(format: String, timeZone: TimeZone = TimeZone.getDefault()): String =
    SimpleDateFormat(format, Locale.ENGLISH).apply { setTimeZone(timeZone) }.format(this)

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): Date? = SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy").parse(this)

fun Date.toRelative(): String =
    DateUtils.getRelativeTimeSpanString(this.time, now.time, DateUtils.DAY_IN_MILLIS).toString()

