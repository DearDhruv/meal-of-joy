/*
 *  TwitterSearchViewState.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.view

import com.mealofjoy.android.architecture.*
import com.mealofjoy.android.model.TwitterSearch

data class TwitterSearchViewState(
    override val loading: MJLoading = MJLoading(),
    override val error: MJError = MJError(),
    val twitter: TwitterSearch.Twitter? = null
) : ViewState

sealed class TwitterSearchViewEvent : ViewStateEvent() {
    data class Search(val term: String) : TwitterSearchViewEvent()
    data class TweetSelected(val tweetId: String) : TwitterSearchViewEvent()
    data class ProfileClicked(val username: String) : TwitterSearchViewEvent()
}

sealed class TwitterSearchViewEffect : ViewStateEffect() {
    data class Navigate(val tweetId: String) : TwitterSearchViewEffect()
}


