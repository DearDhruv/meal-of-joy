/*
 *  GraphResponseConverter.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.converter

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import okhttp3.ResponseBody
import retrofit2.Converter

open class GraphResponseConverter<T> constructor(
    private val adapter: TypeAdapter<T>,
    private val gson: Gson
) : Converter<ResponseBody, T> {
    override fun convert(value: ResponseBody): T? {
        var ret: T? = null
        value.use {
            ret = adapter.read(gson.newJsonReader(value.charStream()))
        }
        return ret
    }
}
