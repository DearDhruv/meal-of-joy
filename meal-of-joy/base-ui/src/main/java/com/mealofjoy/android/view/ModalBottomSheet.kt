/*
 *  ModalBottomSheet.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mealofjoy.android.R
import com.mealofjoy.android.extensions.dp
import com.mealofjoy.android.extensions.maximumHeight
import kotlinx.android.synthetic.main.dialog_fragment_modal_bottom_sheet.*

abstract class ModalBottomSheet(@NavigationRes val initialGraphId: Int) :
    BottomSheetDialogFragment(),
    ModalBottomSheetCallback {

    lateinit var navController: NavController

    protected abstract fun initSheet()

    protected open fun topLevelDestinations(): Set<Int> {
        return setOf(navController.graph.startDestination)
    }

    protected fun updateNavGraph(@NavigationRes navGraphResId: Int) {
        navController.setGraph(navGraphResId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(
            R.layout.dialog_fragment_modal_bottom_sheet,
            container,
            false
        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // We can't inflate the NavHostFragment from XML because it will crash the 2nd time the dialog is opened
        val navHost = NavHostFragment()
        childFragmentManager.beginTransaction().replace(R.id.navHost, navHost)
            .commitNow()

        navController = navHost.navController.apply { setGraph(initialGraphId) }

        toolbar.setupWithNavController(
            navController,
            configuration = AppBarConfiguration(topLevelDestinationIds = topLevelDestinations())
        )

        initSheet()
    }

    protected fun updateOptionsMenu(show: Boolean) {
        if (show) {
            toolbar.menu.clear()
            toolbar.inflateMenu(R.menu.close_sheet)
            toolbar.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.close -> {
                        dismissSheet()
                        true
                    }
                    else -> false
                }
            }
        } else {
            toolbar.menu.clear()
        }
    }

    override fun dismissSheet() {
        dismiss()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener { dialog ->
                val d = dialog as BottomSheetDialog
                val bottomSheet =
                    d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as? FrameLayout
                // set bottom sheet height and peekHeight to equal values
                // this, in combination with isHideable = false, creates a static modal locked sheet
                val maxHeight = maximumHeight(activity?.findViewById(R.id.toolbar), 24.dp)
                bottomSheet?.let {
                    BottomSheetBehavior.from(it).apply {
                        isHideable = false
                        peekHeight = maxHeight
                    }
                }
                bottomSheet?.layoutParams?.height = maxHeight

            }
            // Normally the dialog would close on back press. We override this behaviour and check if we can navigate back
            // If we can't navigate back we return false triggering the default implementation closing the dialog
            setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                    val popped =
                        if (navController.popBackStack()) true else findNavController().popBackStack()
                    popped
                } else {
                    false
                }
            }
        }
    }
}
