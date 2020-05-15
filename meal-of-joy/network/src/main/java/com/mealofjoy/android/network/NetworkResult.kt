/*
 *  NetworkState.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network

sealed class NetworkResult<out T> {
    data class Success<T>(val body: T) : NetworkResult<T>()
    data class Failure<T>(
        val exception: Throwable? = null,
        val errorResponse: String? = null
    ) : NetworkResult<T>()
}
