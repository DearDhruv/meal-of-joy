/*
 *  StringExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 24-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.extensions

// EEE MMM dd HH:mm:ss Z yyyy
fun String.toRelativeDate(): String? = this.formatDate()?.toRelative()

