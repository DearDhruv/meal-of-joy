/*
 *  TwitterSearchNetworkRepository.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */
package com.mealofjoy.android.network.repository.twitter

import com.mealofjoy.android.model.TwitterSearchResponse
import com.mealofjoy.android.network.NetworkResult

interface TwitterSearchNetworkRepository {

    fun twitterSearch(
        identity: String,
        limit: Int,
        cb: ((NetworkResult<TwitterSearchResponse>) -> Unit)
    )

//    fun loadTweetById(
//        tweetId: String,
//        cb: (TwitterSearchResponse) -> Unit
//    )

}
