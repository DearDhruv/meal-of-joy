/*
 *  TwitterSearchRepositoryImpl.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.repository

import com.mealofjoy.android.architecture.LoadResult
import com.mealofjoy.android.network.NetworkResult
import com.mealofjoy.android.network.repository.RequestResolver
import com.mealofjoy.android.network.repository.twitter.TwitterSearchNetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TwitterSearchRepositoryImpl(val network: TwitterSearchNetworkRepository) :
    TwitterSearchRepository,
    RequestResolver, CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun twitterSearch(identity: String, cb: (LoadResult) -> Unit) {
        network.twitterSearch("identifier", identity, 20) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    result.body.data?.let { cb.invoke(LoadResult(it.twitterSearch)) }
                }
                is NetworkResult.Failure -> {
                    cb.invoke(LoadResult(error = Throwable(result.errorResponse)))
                }
            }
        }
    }

}
