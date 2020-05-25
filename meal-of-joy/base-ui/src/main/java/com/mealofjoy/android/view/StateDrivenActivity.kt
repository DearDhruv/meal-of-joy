/*
 *  StateDrivenActivity.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.mealofjoy.android.architecture.*
import com.mealofjoy.android.extensions.toast

abstract class StateDrivenActivity<
        S : ViewState,
        E : ViewStateEvent,
        X : ViewStateEffect,
        V : BaseStatefulViewModel<S, E, X>> : AppCompatActivity() {

    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenStarted { whenStarted() }
        lifecycleScope.launchWhenResumed { whenResumed() }

        viewModel.state.observe(this, viewStateObserver)
        viewModel.effects.observe(this, viewEffectsObserver)
    }

    protected open fun whenStarted() = Unit
    protected open fun whenResumed() = Unit

    abstract fun renderViewState(viewState: S)
    abstract fun renderViewEffect(effect: X)
    abstract fun handleLoading(loader: MJLoading)

    protected open fun handleError(error: MJError) {
        if (error.hasErrors()) {
            toast(error.message(this))
        }
    }

    private val viewStateObserver = Observer<S> { renderViewState(it) }

    private val viewEffectsObserver = Observer<X> { renderViewEffect(it) }

    private lateinit var progressDialogFragment: ProgressDialogFragment

    // this can be state driven as well
    fun showProgressDialog(message: String) {
        val tag = "progress"
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val prev: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)

        progressDialogFragment = ProgressDialogFragment.newInstance(message)
        progressDialogFragment.show(ft, tag)
    }

    fun hideProgressDialog() {
        if (::progressDialogFragment.isInitialized) {
            progressDialogFragment.dismiss()
        }
    }
}

