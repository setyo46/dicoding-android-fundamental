package com.setyo.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.adapter.Event
import com.setyo.githubuser.api.ApiConfig
import com.setyo.githubuser.data.GithubResponse
import com.setyo.githubuser.data.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUser = MutableLiveData<List<GithubUser>>()
    val listUser: LiveData<List<GithubUser>> = _listUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _textToast = MutableLiveData<Event<String>>()
    val textToast: LiveData<Event<String>> = _textToast

    val searchQuery = MutableLiveData<String>()

    fun findGithubUser(userInput: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getListUser(userInput)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                      _listUser.value = response.body()?.items
                } else {
                    _textToast.value = Event("Username No Found")
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                _textToast.value = Event("Not Connected to Internet")
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val GITHUB_USERNAME = "arif"
    }

    init {
        findGithubUser(GITHUB_USERNAME)
    }
}