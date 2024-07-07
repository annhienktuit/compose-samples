package com.example.jetcaster.core.data.network

import android.util.Log
import com.example.jetcaster.core.data.Dispatcher
import com.example.jetcaster.core.data.JetcasterDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class NetworkFetcher @Inject constructor(
    private val okHttpClient: OkHttpClient,
    @Dispatcher(JetcasterDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {
    operator fun invoke(requestUrl: String): Flow<NetworkResponse> = flow {
        val request = Request.Builder()
            .url(requestUrl)
            .build()

        try {
            val response = okHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val responseBody = response.body?.string()
                if (responseBody != null) {
                    emit(NetworkResponse.Success(responseBody))
                } else {
                    emit(NetworkResponse.Error(IOException("Empty response body")))
                }
            } else {
                emit(NetworkResponse.Error(IOException("Network request failed with code ${response.code}")))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e))
        }
    }.flowOn(ioDispatcher)
}

sealed class NetworkResponse {
    data class Error(
        val throwable: Throwable?,
    ): NetworkResponse()

    data class Success(
        val payload: Any
    ): NetworkResponse()
}