package com.example.jetcaster.core.data.network

import com.example.jetcaster.core.data.database.model.ProfileInfo
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by nhiennha on 16/07/2024.
 */
interface ApiService {
    //e1eada73a68dd014364f
    @GET("/{userId}")
    suspend fun getProfileInfo(@Path("userId") userId: String): ProfileInfo
}