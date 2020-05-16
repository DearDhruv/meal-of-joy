/*
 *  GraphQLError.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.model

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

data class Extension(val code: String? = null)
data class GraphQLError(val message: String, val extensions: Extension? = null)

fun <T> GraphQLError.asException(): Throwable {
    val responseCode = extensions?.code?.toIntOrNull() ?: 0

    return if (responseCode > 0) {
        // http exception
        // create response for retrofit
        val res = Response.error<T>(
            responseCode, ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                message
            )
        )
        HttpException(res)
    } else {
        GraphQLException(message)
    }
}

abstract class GraphResult<T, R>(
    open val errors: List<GraphQLError>? = emptyList(),
    val data: T? = null
) {
    fun hasErrors(): Boolean = errors?.isNotEmpty() ?: false
    abstract fun hasData(): Boolean
    abstract fun result(): R?
}

class GraphQLException(override val message: String) : Throwable(message)
