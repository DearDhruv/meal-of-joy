/*
 *  NetworkConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 16-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.model

data class NetworkConfig(
    val baseUrl: String = "http://localhost",
    val recordProxyEndpoint: String? = null,
    val loadAndPlaybackFileBasedMappings: Boolean = false,
    val port: Int? = null,
    val isWireMockServer: Boolean = true,
    val versioned: Int? = null
) {
    val fullUrl by lazy {
        (port?.let { "$baseUrl:$port" } ?: baseUrl).run {
            if (!baseUrl.endsWith("/")) {
                if (versioned != null) {
                    plus("/").plus("v${versioned}/")
                } else {
                    plus("/")
                }
            } else {
                if (versioned != null) {
                    plus("v$versioned/")
                } else {
                    this
                }
            }
        }
    }

    val isLocalhostServer by lazy {
        baseUrl.contains("//127.0.0.1") || baseUrl.contains("//localhost")
    }
}
