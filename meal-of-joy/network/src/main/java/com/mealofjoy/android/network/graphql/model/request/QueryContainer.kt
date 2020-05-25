/*
 *  QueryContainer.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.model.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class QueryContainer @JvmOverloads constructor(
    var query: String = "",
    val variables: @RawValue MutableMap<String, Any> = mutableMapOf()
) : Parcelable
