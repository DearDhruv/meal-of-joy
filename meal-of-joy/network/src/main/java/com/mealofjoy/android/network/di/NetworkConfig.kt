/*
 *  NetworkConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.di

import com.mealofjoy.android.di.Component
import com.mealofjoy.android.model.NetworkConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

interface NetworkComponent : Component {
    val retrofit: Retrofit
}

open class BaseNetworkComponent(
    networkConfig: NetworkConfig,
    converters: List<Converter.Factory> = emptyList(),
    callAdapters: List<CallAdapter.Factory> = emptyList(),
    interceptors: List<Interceptor> = emptyList()
) : NetworkComponent {

    private val okHttpClientBuilder = OkHttpClient.Builder().apply {
        readTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
        interceptors.forEach { interceptor -> addInterceptor(interceptor) }
    }

    override val retrofit: Retrofit = Retrofit.Builder().apply {
        baseUrl(networkConfig.fullUrl)
        converters.forEach { addConverterFactory(it) }
        callAdapters.forEach { addCallAdapterFactory(it) }
        client(okHttpClientBuilder.build())
    }.build()
}
