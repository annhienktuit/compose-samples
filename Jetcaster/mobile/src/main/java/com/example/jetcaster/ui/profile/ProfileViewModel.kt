package com.example.jetcaster.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetcaster.core.data.network.NetworkFetcher
import com.example.jetcaster.core.data.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val networkFetcher: NetworkFetcher
): ViewModel() {
    private val _userData = MutableLiveData<NetworkResponse>()
    val userData: LiveData<NetworkResponse> get() = _userData
    init {
        viewModelScope.launch {
            networkFetcher("https://api.npoint.io/e1eada73a68dd014364f")
                .catch { e -> _userData.postValue(NetworkResponse.Error(e)) }
                .collect { response -> _userData.postValue(response) }
        }
    }
}