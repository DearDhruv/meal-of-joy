/*
 *  GraphContainer.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.model.body

import com.mealofjoy.android.network.graphql.model.attribute.GraphError


data class GraphContainer<T>(
    val data: T?,
    val errors: List<GraphError>? = emptyList()
)
