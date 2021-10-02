/*
 *  EnvironmentDimension.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android

import android.app.Application
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mealofjoy.android.model.NetworkConfig
import com.mealofjoy.android.network.converters.NullStringToEmptyAdapterFactory
import com.mealofjoy.android.network.di.BaseNetworkComponent
import com.mealofjoy.android.network.di.NetworkComponent
import com.mealofjoy.android.network.graphql.converter.GraphQLConverter
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

private fun gson(): Gson = GsonBuilder()
    .registerTypeAdapterFactory(NullStringToEmptyAdapterFactory<String>())
    .create()

fun Application.environmentDimensionNetworkComponent(): NetworkComponent {
    return BaseNetworkComponent(
        networkConfig = NetworkConfig(
            baseUrl = "https://www.deardhruv.com" // todo: change this the correct server - this is just to test the network
        ),
        interceptors = listOf(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }),
        converters = listOf(GraphQLConverter.create(this), GsonConverterFactory.create(gson()))
    )
}
