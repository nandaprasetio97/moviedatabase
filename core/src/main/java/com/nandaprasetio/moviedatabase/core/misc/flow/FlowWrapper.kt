package com.nandaprasetio.moviedatabase.core.misc.flow

import kotlinx.coroutines.flow.Flow

class FlowWrapper<T> {
    private var _flow: Flow<T>? = null
    val flow: Flow<T>
        get() = _flow ?: throw NullPointerException("Flow must be assigned.")

    fun assignFlow(onAssignFlow: () -> Flow<T>): FlowWrapper<T> {
        if (_flow == null) {
            _flow = onAssignFlow()
        }
        return this
    }

    fun resetFlowToNull() {
        _flow = null
    }
}