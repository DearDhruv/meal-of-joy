/*
 *  TwitterSearchNetworkRepositoryImpl.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.repository.twitter

import com.mealofjoy.android.model.TwitterSearchResponse
import com.mealofjoy.android.network.NetworkResult
import com.mealofjoy.android.network.graphql.model.request.QueryContainerBuilder
import com.mealofjoy.android.network.repository.RequestResolver
import com.mealofjoy.android.network.services.TwitterService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TwitterSearchNetworkRepositoryImpl(
    private val webService: TwitterService
) : TwitterSearchNetworkRepository,
    RequestResolver,
    CoroutineScope by CoroutineScope(Dispatchers.IO) {

    override fun twitterSearch(
        identifier: String,
        identity: String,
        limit: Int,
        cb: (NetworkResult<TwitterSearchResponse>) -> Unit
    ) {
        launch {
            cb.invoke(handleResponse {
                runBlocking(coroutineContext) {
                    // currently query is not using the other stuff
                    webService.getTwitterSearchAsync(
                        QueryContainerBuilder()
//                            .putVariable("identifier", identifier)
                            .putVariable("q", identity)
//                            .putVariable("limit", limit)
                    )
                }
            })
        }
    }

}
