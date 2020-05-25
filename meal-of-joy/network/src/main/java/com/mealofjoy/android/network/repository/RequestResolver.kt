/*
 *  RequestResolver.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.repository

import com.mealofjoy.android.network.NetworkResult
import retrofit2.Response

interface RequestResolver {
    suspend fun <T> handleResponse(
        response: () -> Response<T>
    ): NetworkResult<T> {
        try {
            val res = response.invoke()
            if (res.isSuccessful) {
                val body = res.body()
                if (body != null) {
                    return NetworkResult.Success(body)
                }
            }
            return NetworkResult.Failure(errorResponse = res.message())
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return NetworkResult.Failure(exception = e)
        }
    }
}
