/*
 *  QuickTaskIntentService.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 25-06-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */


package com.mealofjoy.android.network

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.mealofjoy.android.Logger

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
class QuickTaskIntentService : IntentService("QuickTaskIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        when (intent?.action) {
            ACTION_FETCH_NEW_TWEETS ->
                handleActionMoreTweets(intent.getStringExtra(EXTRA_PARAM_SEARCH_TERM).orEmpty())

            ACTION_LOG ->
                handleActionLog(intent.getStringExtra(EXTRA_PARAM_LOG_MG).orEmpty())

        }
    }

    private fun handleActionMoreTweets(term: String) {
        Logger.d(TAG, "search -> $term")
    }

    private fun handleActionLog(msg: String) {
        Logger.d(TAG, "log -> $msg")
    }

    companion object {

        private const val ACTION_FETCH_NEW_TWEETS =
            "com.mealofjoy.android.network.action.fetch_new_tweets"
        private const val ACTION_LOG = "com.mealofjoy.android.network.action.log"

        private const val EXTRA_PARAM_SEARCH_TERM =
            "com.mealofjoy.android.network.extra.search_term"
        private const val EXTRA_PARAM_LOG_MG =
            "com.mealofjoy.android.network.extra.log_mg"
        private const val TAG = "QuickTaskIntentService"

        @JvmStatic
        fun startActionSearch(context: Context, term: String) {
            val intent = Intent(context, QuickTaskIntentService::class.java).apply {
                action = ACTION_FETCH_NEW_TWEETS
                putExtra(EXTRA_PARAM_SEARCH_TERM, term)
            }
            context.startService(intent)
        }

        @JvmStatic
        fun startActionLog(context: Context, msg: String) {
            val intent = Intent(context, QuickTaskIntentService::class.java).apply {
                action = ACTION_LOG
                putExtra(EXTRA_PARAM_LOG_MG, msg)
            }
            context.startService(intent)
        }
    }
}