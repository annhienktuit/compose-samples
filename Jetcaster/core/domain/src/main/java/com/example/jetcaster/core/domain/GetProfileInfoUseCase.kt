package com.example.jetcaster.core.domain

import com.example.jetcaster.core.data.database.model.ProfileInfo
import com.example.jetcaster.core.data.repository.ProfileInfoStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by nhiennha on 16/07/2024.
 */
class GetProfileInfoUseCase @Inject constructor(
    private val profileInfoStore: ProfileInfoStore
) {
    operator fun invoke(userId: String): Flow<ProfileInfo> {
        return profileInfoStore.getProfileInfo(userId)
    }
}