/*
 *  GraphQLConverter.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.converter

import android.content.Context
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.mealofjoy.android.network.graphql.annotation.processor.GraphQueryProcessor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class GraphQLConverter private constructor(context: Context) : Converter.Factory() {

    private val graphProcessor = GraphQueryProcessor(context)
    private val gson = GsonBuilder().enableComplexMapKeySerialization().setLenient().create()

    /**
     * Response body converter delegates logic processing to a child class that handles
     * wrapping and deserialization of the json response data.
     * @see GraphResponseConverter
     * <br/>
     *
     * @param annotations All the annotation applied to the requesting Call method
     *                    @see retrofit2.Call
     * @param retrofit The retrofit object representing the response
     * @param type The generic type declared on the Call method
     */
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return when (type) {
            is ResponseBody -> super.responseBodyConverter(type, annotations, retrofit)
            else -> GraphResponseConverter(gson.getAdapter(TypeToken.get(type)), gson)
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? = GraphRequestConverter(methodAnnotations, graphProcessor, gson)

    companion object {
        val MEDIA_TYPE = "application/json; charset=UTF-8".toMediaType()

        @JvmStatic
        fun create(context: Context): GraphQLConverter {
            return GraphQLConverter(context)
        }
    }
}
