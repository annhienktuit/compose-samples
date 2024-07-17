package com.example.jetcaster.core.data.repository

import com.example.jetcaster.core.data.database.model.ProfileInfo
import com.example.jetcaster.core.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by nhiennha on 16/07/2024.
 */

interface ProfileInfoStore {

    fun getProfileInfo(userId: String): Flow<ProfileInfo>

}
class RemoteProfileInfoStore @Inject constructor(
    private val apiService: ApiService
): ProfileInfoStore {
    override fun getProfileInfo(userId: String): Flow<ProfileInfo> = flow {
        emit(apiService.getProfileInfo(userId))
    }
}