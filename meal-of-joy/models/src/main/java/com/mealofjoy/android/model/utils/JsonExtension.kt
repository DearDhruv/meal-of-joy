package com.mealofjoy.android.model.utils

import com.google.gson.Gson


/*
 *  JsonExtension.kt
 *
 *  Created by Dhruv Patel on 02-10-2021.
 */


fun toJsonString(clazz: Any): String {
    return Gson().toJson(clazz)
}