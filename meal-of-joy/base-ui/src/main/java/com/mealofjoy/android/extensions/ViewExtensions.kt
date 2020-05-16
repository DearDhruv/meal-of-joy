/*
 *  ViewExtensions.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDecorations(vararg decoration: RecyclerView.ItemDecoration) {
    decoration.forEach {
        this.addItemDecoration(it)
    }
}

@SuppressLint("ClickableViewAccessibility")
fun EditText.onRightDrawableClicked(onClicked: (view: EditText) -> Unit) {
    this.setOnTouchListener { v, event ->
        var hasConsumed = false
        if (v is EditText) {
            if (event.x >= v.width - v.totalPaddingRight) {
                if (event.action == MotionEvent.ACTION_UP) {
                    onClicked(this)
                }
                hasConsumed = true
            }
        }
        hasConsumed
    }
}

fun EditText.showKeyboard() {
    this.requestFocus()
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard() {
    try {
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(windowToken, 0)
    } catch (e: Exception) {
    }
}

fun AppCompatActivity.showDialogFragment(dialog: AppCompatDialogFragment, tag: String) {
    dialog.show(supportFragmentManager, tag)
}

fun AppCompatActivity.enableFullscreen() {
    with(window) {
        setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}

fun AppCompatActivity.visitUrl(website: String) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(website)
        )
    )
}

fun Fragment.visitUrl(website: String) {
    startActivity(
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse(website)
        )
    )
}

fun View.vibrateCompat(vibrator: Vibrator, millis: Long = 1L, amplitude: Int = 25) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(millis, amplitude))
    } else {
        performHapticFeedback(
            HapticFeedbackConstants.VIRTUAL_KEY,
            HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING
        );
    }
}


fun View.handleLayout(onLayout: (() -> Unit)? = null) {
    if (this.isLaidOut) {
        onLayout?.invoke()
    } else {
        this.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                onLayout?.invoke()
                this@handleLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
    }
}


