/*
 *  MainFragment.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 11/05/2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mealofjoy.android.R
import com.mealofjoy.android.analytics.DemoAnalytics
import com.mealofjoy.android.analytics.DemoAnalyticsImpl
import com.mealofjoy.android.analytics.di.AnalyticsComponent
import com.mealofjoy.android.di.component
import com.mealofjoy.android.extensions.getViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val analyticsComponent: AnalyticsComponent = "analytics".component()
    private val analytics: DemoAnalytics = DemoAnalyticsImpl(analyticsComponent.analyticsManager)

    private val viewModel: MainViewModel? by lazy {
        activity?.getViewModel { MainViewModel.create(analytics) }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel?.logAppOpened()
    }

}