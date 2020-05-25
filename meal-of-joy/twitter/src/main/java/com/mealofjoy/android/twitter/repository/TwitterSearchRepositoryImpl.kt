/*
 *  TwitterSearchRepositoryImpl.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.twitter.repository

import com.mealofjoy.android.architecture.LoadResult
import com.mealofjoy.android.network.NetworkResult
import com.mealofjoy.android.network.repository.RequestResolver
import com.mealofjoy.android.network.repository.twitter.TwitterSearchNetworkRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class TwitterSearchRepositoryImpl(private val network: TwitterSearchNetworkRepository) :
    TwitterSearchRepository,
    RequestResolver, CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun twitterSearch(query: String?, count: Int, cb: (LoadResult) -> Unit) {
        network.twitterSearch(query ?: "deardhruv", count.coerceAtMost(100)) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    if (result.body.hasData()) {
                        result.body.result()?.let { cb.invoke(LoadResult(it)) }
                    }
                }
                is NetworkResult.Failure -> {
                    cb.invoke(LoadResult(error = Throwable(result.errorResponse)))
                }
            }
        }
    }

}
