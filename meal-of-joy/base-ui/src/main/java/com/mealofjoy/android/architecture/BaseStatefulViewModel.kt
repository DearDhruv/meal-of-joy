/*
 *  BaseStatefulViewModel.kt
 *  MealOfJoy
 *  <p>
 *  Created by Dhruv Patel on 17-05-2020.
 *  Copyright © 2020 MealOfJoy. All rights reserved.
 */

package com.mealofjoy.android.architecture

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.zhuinden.eventemitter.EventEmitter
import com.zhuinden.eventemitter.EventSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseStatefulViewModel<S : ViewState, E : ViewStateEvent, X : ViewStateEffect>(
    private val initialState: S,
    val savedStateHandle: SavedStateHandle? = null
) : ViewModel(), ViewModelContract<E>, CoroutineScope by CoroutineScope(Dispatchers.Main) {

    val state: MediatorLiveData<S> = MediatorLiveData()

    private val viewState: MutableLiveData<S> = MutableLiveData()

    protected fun getLastState(): S? = state.value ?: initialState

    protected val effectsEmitter = EventEmitter<X>()
    val effects: EventSource<X> get() = effectsEmitter

    init {
        state.addSource(viewState) { state.value = it }
        setState { initialState }
    }

    protected fun setState(stateModifier: S.() -> S) {
        viewModelScope.launch {
            viewState.value = stateModifier.invoke(state.value ?: initialState)
        }
    }

    protected fun <T> addSource(source: LiveData<T>, onChanged: Observer<T>) {
        state.addSource(source, onChanged)
    }

    protected abstract fun emitEffect(effect: X)

    protected fun generateError(
        exception: Throwable? = null,
        title: String?,
        message: String?
    ): SEError = SEError(exception = exception, titleString = title, messageString = message)

    protected fun generateError(
        exception: Throwable?,
        @StringRes titleResId: Int?,
        @StringRes messageResId: Int?
    ): SEError =
        SEError(exception = exception, titleResId = titleResId, messageResId = messageResId)

    abstract fun informOfLoading(message: String)

    abstract fun informOfError(
        exception: Throwable? = null,
        title: String? = null,
        message: String?
    )

    abstract fun informOfError(
        exception: Throwable? = null,
        @StringRes titleResId: Int? = null,
        @StringRes messageResId: Int?
    )
}

interface ViewModelContract<E : ViewStateEvent> {
    fun process(event: E)
}


interface ViewState {
    val loading: SELoading
    val error: SEError
}

open class ViewStateEffect
open class ViewStateEvent
