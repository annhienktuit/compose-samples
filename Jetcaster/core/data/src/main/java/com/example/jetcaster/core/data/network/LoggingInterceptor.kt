package com.example.jetcaster.core.data.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Created by nhiennha on 18/07/2024.
 */
class LoggingInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()
        println("nhiennha Requested URL: $url") // Log or handle the URL as needed
        return chain.proceed(request)
    }
}