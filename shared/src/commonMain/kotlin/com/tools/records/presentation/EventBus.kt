package com.tools.records.presentation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterIsInstance

object EventBus {
    private val _eventFlow = MutableSharedFlow<Any>()
    val eventFlow = _eventFlow.asSharedFlow()

    inline fun <reified T> addToObserve() = eventFlow.filterIsInstance<T>()

    suspend fun post(event: Any) = _eventFlow.emit(event)
}
