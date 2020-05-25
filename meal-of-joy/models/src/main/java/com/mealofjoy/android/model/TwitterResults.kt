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
    GraphResult<TwitterSearch.Data, TwitterSearch.Twitter>() {
    override fun hasData(): Boolean = data != null
    override fun result(): TwitterSearch.Twitter? = data?.twitter
}

// endregion

