package com.vpr.scheduleapp.data.api

interface ApiCallback<T> {
    fun onSuccess(t: T?)

    fun onError(error: String)

    fun onException(exception: Throwable)
}