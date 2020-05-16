/*
 *  GraphError.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.model.attribute

data class GraphError(
    val message: String?,
    val status: Int,
    val locations: List<Map<String, Int>>?
) {

    override fun toString(): String {
        return "GraphError{" +
                "message='" + message + '\''.toString() +
                ", status=" + status +
                ", locations=" + locations +
                '}'.toString()
    }
}
