/*
 *  StateDrivenLazyFragment.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mealofjoy.android.architecture.*
import com.mealofjoy.android.extensions.toast

abstract class StateDrivenLazyFragment<S : ViewState, E : ViewStateEvent, X : ViewStateEffect, V : BaseStatefulViewModel<S, E, X>?> :
    LazyFragment() {

    abstract val viewModel: V?

    private val viewStateObserver = Observer<S> {
        renderViewState(it)
    }

    private val viewEffectsObserver = Observer<X> {
        renderViewEffect(it)
    }

    abstract fun renderViewState(viewState: S)
    abstract fun renderViewEffect(effect: X)
    abstract fun handleLoading(loader: SELoading)

    open suspend fun whenStarted() = Unit
    open suspend fun whenResumed() = Unit

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        viewLifecycleOwner.lifecycleScope.launchWhenStarted { whenStarted() }
        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            whenResumed()
        }

        viewModel?.state?.observe(viewLifecycleOwner, viewStateObserver)
        viewModel?.effects?.observe(viewLifecycleOwner, viewEffectsObserver)
    }

    protected open fun handleError(error: SEError) {
        if (error.hasErrors()) {
            context?.let {
                toast(error.message(it))
            }
        }
    }
}
