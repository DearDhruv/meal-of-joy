/*
 *  BottomSheetDialogFragment.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import android.graphics.Rect
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mealofjoy.android.R

fun BottomSheetDialogFragment.maximumHeight(
    toolbar: Toolbar? = activity?.findViewById(R.id.toolbar),
    padding: Int
): Int {
    val toolbarHeight = toolbar?.height ?: 0

    val statusbar = Rect().apply { activity?.window?.decorView?.getWindowVisibleDisplayFrame(this) }
    val windowHeight = statusbar.bottom

    return windowHeight - toolbarHeight - statusbar.top - padding
}
