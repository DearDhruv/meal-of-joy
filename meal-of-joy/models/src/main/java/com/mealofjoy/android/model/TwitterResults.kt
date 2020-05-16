/*
 *  TwitterResults.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.model

/**
 * Twitter
 */

class TwitterSearchResponse :
    GraphResult<TwitterSearchResult, Twitter>() {
    override fun hasData(): Boolean = data?.twitterSearch != null
    override fun result(): Twitter? = data?.twitterSearch?.data?.twitter
}

data class TwitterSearchResult(val twitterSearch: TwitterSearch)

// endregion

