/*
 *  TwitterService.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.services

import com.mealofjoy.android.model.TwitterSearchResponse
import com.mealofjoy.android.network.graphql.annotation.GraphQuery
import com.mealofjoy.android.network.graphql.model.request.QueryContainerBuilder
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TwitterService {

    @POST("test/deardhruv_profile.json")
    @GraphQuery("GetTwitterSearchByIdentity")
    suspend fun getTwitterSearchAsync(@Body query: QueryContainerBuilder): Response<TwitterSearchResponse>

//    @POST("graphql")
//    @GraphQuery("GetTwitterSearchByIdentity")
//    suspend fun getTwitterSearchAsync(@Body query: QueryContainerBuilder): Response<TwitterSearchResponse>

//    @POST("graphql")
//    @GraphQuery("GetTweetById")
//    suspend fun getTweetByIdAsync(@Body query: QueryContainerBuilder): Response<TwitterSearchResponse>
}
