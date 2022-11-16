package com.vpr.scheduleapp.data.remote

interface ApiCallback<T> {
    fun onSuccess(t: T?)

    fun onError(error: String)

    fun onException(exception: Throwable)
}