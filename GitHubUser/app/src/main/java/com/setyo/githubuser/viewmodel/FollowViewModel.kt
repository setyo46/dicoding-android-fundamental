package com.setyo.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.adapter.Event
import com.setyo.githubuser.api.ApiConfig
import com.setyo.githubuser.data.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowViewModel: ViewModel() {

    private val _listUserFollower = MutableLiveData<List<GithubUser>>()
    val listUserFollower: LiveData<List<GithubUser>> = _listUserFollower

    private val _listUserFollowing = MutableLiveData<List<GithubUser>>()
    val listUserFollowing: LiveData<List<GithubUser>> = _listUserFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _textToast = MutableLiveData<Event<String>>()
    val textToast: LiveData<Event<String>> = _textToast

    fun followerUser(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<GithubUser>>{
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUserFollower.value = response.body()
                } else {
                    _textToast.value = Event("Username No Found")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Not Connected to Internet")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun followingUser(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<GithubUser>>{
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUserFollowing.value = response.body()
                } else {
                    _textToast.value = Event("Username No Found")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Not Connected to Internet")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "FollowViewModel"
    }
}
