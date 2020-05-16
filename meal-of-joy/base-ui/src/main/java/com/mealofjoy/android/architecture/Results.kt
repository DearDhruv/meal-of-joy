/*
 *  Results.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.architecture

data class LoadResult(val result: Any? = null, val error: Throwable? = null)
data class SaveResult(val result: Any? = null, val error: Throwable? = null)
data class DeleteResult(val result: Any? = null, val error: Throwable? = null)
