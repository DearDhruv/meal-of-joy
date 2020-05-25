/*
 *  TwitterSearchViewModel.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.view

import androidx.lifecycle.viewModelScope
import com.mealofjoy.android.architecture.BaseStatefulViewModel
import com.mealofjoy.android.architecture.MJError
import com.mealofjoy.android.architecture.MJLoading
import com.mealofjoy.android.model.TwitterSearch
import com.mealofjoy.android.twitter.usecases.TwitterSearchUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TwitterSearchViewModel private constructor(
    private val twitterSearchUsecase: TwitterSearchUsecase
) : BaseStatefulViewModel<TwitterSearchViewState, TwitterSearchViewEvent, TwitterSearchViewEffect>(
    initialState = TwitterSearchViewState()
) {
    override fun emitEffect(effect: TwitterSearchViewEffect) {
    }

    override fun informOfLoading(message: String) {
        viewModelScope.launch(Dispatchers.Main) {
            setState {
                copy(loading = MJLoading(loading = true, message = message))
            }
        }
    }

    override fun informOfError(exception: Throwable?, title: String?, message: String?) {
        viewModelScope.launch(Dispatchers.Main) {
            setState {
                copy(
                    loading = MJLoading(loading = false),
                    error = generateError(exception, title, message)
                )
            }
        }
    }

    override fun informOfError(exception: Throwable?, titleResId: Int?, messageResId: Int?) {
        viewModelScope.launch(Dispatchers.Main) {
            setState {
                copy(
                    loading = MJLoading(loading = false),
                    error = generateError(exception, titleResId, messageResId)
                )
            }
        }
    }

    override fun process(event: TwitterSearchViewEvent) {
        when (event) {
            is TwitterSearchViewEvent.Search -> searchTwitter(event.term)
        }
    }

    private fun searchTwitter(term: String) {

        informOfLoading("Searching...")

        twitterSearchUsecase.execute(term, 100) { load ->
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
                            twitter = load.result as TwitterSearch.Twitter
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

