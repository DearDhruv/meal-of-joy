/*
 *  FragmentExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(msg: String) {
    context.toast(msg)
}

fun Fragment.toast(@StringRes msgResId: Int) {
    context.toast(msgResId)
}

fun Context?.toast(msg: String) {
    this?.let { Toast.makeText(it, msg, Toast.LENGTH_SHORT).show() }
}

fun Context?.toast(@StringRes msgResId: Int) {
    this?.let { Toast.makeText(it, it.getString(msgResId), Toast.LENGTH_SHORT).show() }
}
