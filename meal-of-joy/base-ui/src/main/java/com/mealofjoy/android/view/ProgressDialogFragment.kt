/*
 *  ProgressDialogFragment.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 26-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.mealofjoy.android.architecture.R
import kotlinx.android.synthetic.main.progress_dialog_fragment.*

class ProgressDialogFragment private constructor() : AppCompatDialogFragment() {

    private val providedArguments by lazy {
        val message = arguments?.getString(KEY_MESSAGE) ?: "Loading..."
        ProvidedArguments(message)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        isCancelable = false
        return inflater.inflate(R.layout.progress_dialog_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        providedArguments // touch :)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        message.text = providedArguments.message
    }

    private data class ProvidedArguments(val message: String)

    companion object {
        private const val KEY_MESSAGE = "message"

        fun newInstance(message: String): ProgressDialogFragment {
            return ProgressDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_MESSAGE, message)
                }
            }
        }
    }

}