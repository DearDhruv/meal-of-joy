/*
 *  NetworkState.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network

class NetworkState(
    val status: Status = Status.UNKNOWN,
    val msg: String = "",
    val error: Throwable? = null
) {

    enum class Status {
        UNKNOWN,
        RUNNING,
        SUCCESS,
        FAILED
    }

    companion object {

        val LOADED: NetworkState =
            NetworkState(
                Status.SUCCESS,
                "Success"
            )
        val LOADING: NetworkState =
            NetworkState(
                Status.RUNNING,
                "Running"
            )
    }
}
