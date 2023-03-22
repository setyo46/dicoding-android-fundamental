package com.setyo.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.adapter.Event
import com.setyo.githubuser.api.ApiConfig
import com.setyo.githubuser.data.DetailUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {

    private val _listUserFollower = MutableLiveData<DetailUserResponse>()
    val listUserFollower: LiveData<DetailUserResponse> = _listUserFollower

    private val _listUserFollowing = MutableLiveData<DetailUserResponse>()
    val listUserFollowing: LiveData<DetailUserResponse> = _listUserFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _textToast = MutableLiveData<Event<String>>()
    val textToast: LiveData<Event<String>> = _textToast

    companion object {
        private const val TAG = "FollowViewModel"
    }

    fun followingUser(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUserFollower.value = response.body()
                } else {
                    _textToast.value = Event("Username No Found")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Not Connected to Internet")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}
