/*
 *  SessionConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.di

import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.util.CoilUtils
import okhttp3.OkHttpClient

interface SessionComponent : Component {
}

class SessionComponentImpl(private val coreComponent: CoreComponent) : SessionComponent {

    init {
        val imageLoader = ImageLoader.Builder(coreComponent.app)
            .crossfade(true)
            .allowHardware(false)
            .okHttpClient {
                OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(coreComponent.app))
                    .build()
            }
            .componentRegistry() {
                add(GifDecoder())
            }
            .build()

        Coil.setImageLoader(imageLoader)
    }

}


