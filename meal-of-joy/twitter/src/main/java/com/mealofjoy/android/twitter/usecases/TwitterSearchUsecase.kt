/*
 *  TwitterSearchUsecase.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.usecases

import com.mealofjoy.android.architecture.LoadResult
import com.mealofjoy.android.architecture.Parameter2UsecaseWithCallback
import com.mealofjoy.android.twitter.repository.TwitterSearchRepository

class TwitterSearchUsecase(
    private val repository: TwitterSearchRepository
) : Parameter2UsecaseWithCallback<String?, Int, LoadResult>() { // TwitterSearchResult
    override fun execute(p0: String?, count: Int, cb: (LoadResult) -> Unit) {
        repository.twitterSearch(p0 , count, cb)
    }
}
