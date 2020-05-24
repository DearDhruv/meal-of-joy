/*
 *  TwitterSearchRepository.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.repository

import com.mealofjoy.android.architecture.LoadResult

interface TwitterSearchRepository {

    fun twitterSearch(
        identity: String,
        cb: ((LoadResult) -> Unit) // TwitterSearchResult
    )

//    fun loadTweetById(
//        tweetId: String,
//        cb: (TwitterSearchResponse) -> Unit
//    )

}
