package com.venkat.digitalclinic.apiservice.api

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeoutException
import java.util.concurrent.atomic.AtomicInteger

// TODO: UNDER CONSTRUCTION
internal class RetryCallback<T>(
    private val callback: Callback<T>,
    private val maxRetries: Int = 3
) : Callback<T> {
    private val retryCount = AtomicInteger(0)
    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (!response.isSuccessful && retryCount.incrementAndGet() <= maxRetries) {
            call.clone().enqueue(this)
        } else {
            callback.onResponse(call, response)
        }
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (retryCount.incrementAndGet() <= maxRetries) {
            call.clone().enqueue(this)
        } else if (maxRetries > 0) {
            callback.onFailure(
                call,
                TimeoutException(String.format("No retries left after %s attempts.", maxRetries))
            )
        } else {
            callback.onFailure(call, t)
        }
    }
}