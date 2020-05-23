/*
 *  TwitterSearch.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TwitterSearch(
    val data: Data
) : Parcelable

@Parcelize
data class Data(
    val twitter: Twitter
) : Parcelable

@Parcelize
data class Twitter(
//    val user: User,
    val search: List<Search>
) : Parcelable

@Parcelize
data class User(
    val created_at: String?,
    val description: String?,
    val followers_count: Int?,
    val id: String?,
    val name: String?,
    val profile_image_url: String?,
    val screen_name: String?,
//    val tweets: List<Search>,
    val tweets_count: Int,
    val url: String
) : Parcelable

@Parcelize
data class Search(
    val user: User,
    val created_at: String?,
    val id: String?,
    val text: String
) : Parcelable