/*
 *  NumberExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import android.content.res.Resources
import java.text.NumberFormat

val Number.dp: Int
    get() = (this.toFloat() / Resources.getSystem().displayMetrics.density).toInt()
val Number.px: Int
    get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).toInt()

const val HUNDRED = 100.0
const val THOUSAND = 1000.0
const val MILLION = 1000000.0
const val BILLION = 1000000000.0

fun Number.formatted(decimals: Int = 0): String {
    val formatter = "%.${decimals}f"

    return if (decimals == 0) {
        NumberFormat.getInstance().format(this)
    } else when {
        this.toDouble() >= BILLION -> "${formatter.format(this.toDouble() / BILLION)}B"
        this.toDouble() >= MILLION -> "${formatter.format(this.toDouble() / MILLION)}M"
        this.toDouble() >= THOUSAND -> "${formatter.format(this.toDouble() / THOUSAND)}K"
        else -> formatter.format(this.toDouble())
    }
}

class NumberExtensions {
    companion object {
        @JvmStatic
        fun asDp(number: Number): Int = number.dp

        @JvmStatic
        fun asPx(number: Number): Int = number.px
    }
}
