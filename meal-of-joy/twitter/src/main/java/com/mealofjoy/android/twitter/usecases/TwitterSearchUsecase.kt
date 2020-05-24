/*
 *  TwitterSearchUsecase.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.usecases

import com.mealofjoy.android.architecture.LoadResult
import com.mealofjoy.android.architecture.ParameterUsecaseWithCallback
import com.mealofjoy.android.twitter.repository.TwitterSearchRepository

class TwitterSearchUsecase(
    private val repository: TwitterSearchRepository
) : ParameterUsecaseWithCallback<String?, LoadResult>() { // TwitterSearchResult
    override fun execute(p0: String?, cb: (LoadResult) -> Unit) {
        repository.twitterSearch(p0 ?: "deardhruv", cb)
    }
}
