/*
 *  ModalBottomSheetCallback.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import androidx.fragment.app.Fragment

interface ModalBottomSheetCallback {
    fun dismissSheet()
}

fun Fragment.grabBottomSheetCallback(): ModalBottomSheetCallback? {
    var fragment: Fragment? = this
    while (true) {
        val check =
            fragment?.parentFragment ?: break // break out if parent is null; we reached an activity
        if (check is ModalBottomSheetCallback) {
            return check
        }
        fragment = check
    }
    return null
}
