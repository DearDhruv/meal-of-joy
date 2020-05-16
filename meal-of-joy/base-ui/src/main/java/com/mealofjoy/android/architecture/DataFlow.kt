/*
 *  DataFlow.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.architecture

import android.content.Context
import androidx.annotation.StringRes

data class SEError(
    val exception: Throwable? = null,
    val titleString: String? = null,
    val messageString: String? = null,
    @StringRes val titleResId: Int? = null,
    @StringRes val messageResId: Int? = null
) {
    fun hasErrors(): Boolean {
        return !(exception == null &&
                titleString == null &&
                messageString == null &&
                titleResId == null &&
                messageResId == null)
    }

    fun title(context: Context): String {
        return when {
            titleString != null -> titleString
            titleResId != null -> context.getString(titleResId)
            else -> "Error"
        }
    }

    fun message(context: Context): String {
        return when {
            messageString != null -> messageString
            messageResId != null -> context.getString(messageResId)
            exception != null -> exception.localizedMessage
            else -> "unknown error"
        }
    }
}

data class SELoading(
    val loading: Boolean = false,
    val message: String = "Loading..."
) {
    fun isLoading() = loading
}

/**
 * Replacement for using [Any] or [() -> Unit] as your vessel for live data
 */
open class Nudge
