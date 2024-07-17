package com.example.jetcaster.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcaster.core.data.database.model.ProfileInfo
import com.example.jetcaster.core.data.network.NetworkFetcher
import com.example.jetcaster.core.data.network.NetworkResponse
import com.example.jetcaster.core.domain.GetProfileInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileInfoUseCase: GetProfileInfoUseCase
) : ViewModel() {
    private val _profileInfo = MutableStateFlow<ProfileInfo?>(null)
    val profileInfo: StateFlow<ProfileInfo?> get() = _profileInfo

    init {
        viewModelScope.launch {
            getProfileInfoUseCase("e1eada73a68dd014364f").collect { profileInfo ->
                _profileInfo.value = profileInfo
            }
        }
    }
}
