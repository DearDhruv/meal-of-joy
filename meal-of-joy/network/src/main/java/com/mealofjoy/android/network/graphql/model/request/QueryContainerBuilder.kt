/*
 *  QueryContainerBuilder.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.network.graphql.model.request

import android.os.Parcel
import android.os.Parcelable

class QueryContainerBuilder : Parcelable {

    private var container: QueryContainer

    constructor() {
        container = QueryContainer()
    }

    constructor(parcel: Parcel) : this() {
        container =
            parcel.readParcelable(QueryContainer::class.java.classLoader)!! // todo: dhruv - remove force
    }

    fun setQuery(query: String): QueryContainerBuilder {
        container.query = query
        return this
    }

    fun putVariable(key: String, value: Any): QueryContainerBuilder {
        container.variables[key] = value
        return this
    }

    fun containsVariable(key: String): Boolean = container.variables.containsKey(key)

    fun build(): QueryContainer = container

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(container, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QueryContainerBuilder> {
        override fun createFromParcel(parcel: Parcel): QueryContainerBuilder {
            return QueryContainerBuilder(parcel)
        }

        override fun newArray(size: Int): Array<QueryContainerBuilder?> {
            return arrayOfNulls(size)
        }
    }
}
