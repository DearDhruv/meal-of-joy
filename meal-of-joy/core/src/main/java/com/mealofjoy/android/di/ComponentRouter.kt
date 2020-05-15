/*
 *  ComponentRouter.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 12-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.di

import android.app.Application

interface Component

object ComponentRouter {

    val components: MutableMap<String, Component> = mutableMapOf()

    fun init(app: Application, block: Initializer.() -> Unit) {
        Initializer().apply {
            this.inject("core", CoreComponentImpl(app))
            block.invoke(this)
        }
    }

    class Initializer {
        fun inject(tag: String, component: Component) {
            components[tag] = component
        }
    }

    internal fun component(tag: String): Component? = components[tag]
}

fun <T> String.component() = ComponentRouter.component(this) as T
