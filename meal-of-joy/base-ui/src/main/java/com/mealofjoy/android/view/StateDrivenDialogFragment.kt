/*
 *  StateDrivenDialogFragment.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mealofjoy.android.architecture.*
import com.mealofjoy.android.extensions.toast

abstract class StateDrivenDialogFragment<T : ViewState, E : ViewStateEvent, X : ViewStateEffect, V : BaseStatefulViewModel<T, E, X>?> :
    AppCompatDialogFragment() {

    abstract val viewModel: V

    abstract val layoutResId: Int
    internal lateinit var containerView: View

    abstract fun renderViewState(viewState: T)
    abstract fun renderViewEffect(effect: X)
    abstract fun handleLoading(loader: MJLoading)

    open suspend fun whenStarted() = Unit
    open suspend fun whenResumed() = Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        containerView = View.inflate(requireContext(), layoutResId, null)
        return AlertDialog.Builder(requireContext())
            .setView(containerView)
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return containerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { whenStarted() }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            whenResumed()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        viewModel?.state?.observe(viewLifecycleOwner, viewStateObserver)
        viewModel?.effects?.observe(viewLifecycleOwner, viewEffectsObserver)
    }

    abstract fun initView()

    private val viewStateObserver = Observer<T> {
        renderViewState(it)
    }

    private val viewEffectsObserver = Observer<X> {
        renderViewEffect(it)
    }

    protected open fun handleError(error: MJError) {
        if (error.hasErrors()) {
            context?.let {
                toast(error.message(it))
            }
        }
    }

}
