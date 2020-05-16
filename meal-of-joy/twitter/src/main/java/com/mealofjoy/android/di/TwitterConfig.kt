/*
 *  TwitterConfig.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.di

import com.mealofjoy.android.environmentDimensionNetworkComponent
import com.mealofjoy.android.network.di.NetworkComponent
import com.mealofjoy.android.network.repository.twitter.TwitterSearchNetworkRepository
import com.mealofjoy.android.network.repository.twitter.TwitterSearchNetworkRepositoryImpl
import com.mealofjoy.android.network.services.TwitterService
import retrofit2.Retrofit

interface TwitterComponent : Component {
    val network: TwitterNetworkComponent
}

abstract class TwitterNetworkComponent :
    NetworkComponent {
    abstract val twitter: TwitterSearchNetworkRepository
}

class TwitterNetworkComponentImpl(environment: NetworkComponent) : TwitterNetworkComponent() {
    override val retrofit: Retrofit = environment.retrofit
    override val twitter =
        TwitterSearchNetworkRepositoryImpl(retrofit.create(TwitterService::class.java))
}

class TwitterComponentImpl(coreComponent: CoreComponent) : TwitterComponent {
    override val network: TwitterNetworkComponent =
        TwitterNetworkComponentImpl(coreComponent.app.environmentDimensionNetworkComponent())

}
