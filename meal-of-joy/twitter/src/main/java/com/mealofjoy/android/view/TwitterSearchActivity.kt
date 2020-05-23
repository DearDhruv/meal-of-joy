/*
 *  TwitterSearchActivity.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.view

import android.os.Bundle
import com.mealofjoy.android.architecture.MJLoading
import com.mealofjoy.android.di.TwitterComponent
import com.mealofjoy.android.di.component
import com.mealofjoy.android.extensions.getViewModel
import com.mealofjoy.android.repository.TwitterSearchRepositoryImpl
import com.mealofjoy.android.usecases.TwitterSearchUsecase

class TwitterSearchActivity :
    StateDrivenActivity<TwitterSearchViewState, TwitterSearchViewEvent, TwitterSearchViewEffect, TwitterSearchViewModel>() {

    val twiter = "twitter".component<TwitterComponent>()
    val repository = TwitterSearchRepositoryImpl(twiter.network.twitter)

    override val viewModel: TwitterSearchViewModel by lazy {
        getViewModel {
            TwitterSearchViewModel.create(
                twitterSearchUsecase = TwitterSearchUsecase(repository)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.process(TwitterSearchViewEvent.Search("deardhruv"))
    }

    override fun renderViewState(viewState: TwitterSearchViewState) {
    }

    override fun renderViewEffect(effect: TwitterSearchViewEffect) {
    }

    override fun handleLoading(loader: MJLoading) {
    }
}
