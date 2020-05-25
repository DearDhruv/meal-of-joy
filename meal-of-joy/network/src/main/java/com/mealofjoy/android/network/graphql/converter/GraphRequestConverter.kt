/*
 *  GraphRequestConverter.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.converter

import com.google.gson.Gson
import com.mealofjoy.android.network.graphql.annotation.processor.GraphQueryProcessor
import com.mealofjoy.android.network.graphql.model.request.QueryContainerBuilder
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Converter


open class GraphRequestConverter constructor(
    private val annotations: Array<Annotation>,
    private val processor: GraphQueryProcessor,
    private val gson: Gson
) : Converter<QueryContainerBuilder, RequestBody> {
    override fun convert(value: QueryContainerBuilder): RequestBody? {
        return processor.getQuery(annotations)?.let { query ->
            val container = value.setQuery(query).build()
            val json = gson.toJson(container)
            json.toByteArray()
                .toRequestBody(GraphQLConverter.MEDIA_TYPE, 0, json.toByteArray().size)
        }
    }

}
