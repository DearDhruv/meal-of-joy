/*
 *  TwitterSearchViewModel.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.view

import com.mealofjoy.android.architecture.BaseStatefulViewModel
import com.mealofjoy.android.architecture.MJError
import com.mealofjoy.android.architecture.MJLoading
import com.mealofjoy.android.model.Data
import com.mealofjoy.android.twitter.usecases.TwitterSearchUsecase

class TwitterSearchViewModel private constructor(
    private val twitterSearchUsecase: TwitterSearchUsecase
) : BaseStatefulViewModel<TwitterSearchViewState, TwitterSearchViewEvent, TwitterSearchViewEffect>(
    initialState = TwitterSearchViewState()
) {
    override fun emitEffect(effect: TwitterSearchViewEffect) {
    }

    override fun informOfLoading(message: String) {
    }

    override fun informOfError(exception: Throwable?, title: String?, message: String?) {
    }

    override fun informOfError(exception: Throwable?, titleResId: Int?, messageResId: Int?) {
    }

    override fun process(event: TwitterSearchViewEvent) {
        when (event) {
            is TwitterSearchViewEvent.Search -> searchTwitter(event.term)
        }
    }

    private fun searchTwitter(term: String) {
        twitterSearchUsecase.execute(term) { load ->
            when {
                load.error != null -> informOfError(
                    exception = load.error,
                    message = "Failed to search $term"
                )
                else -> {
                    setState {
                        copy(
                            loading = MJLoading(),
                            error = MJError(),
                            data = load.result as Data
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun create(
            twitterSearchUsecase: TwitterSearchUsecase
        ): TwitterSearchViewModel {
            return TwitterSearchViewModel(
                twitterSearchUsecase
            )
        }
    }

}

