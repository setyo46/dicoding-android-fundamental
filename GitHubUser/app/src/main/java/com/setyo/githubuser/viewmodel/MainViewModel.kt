package com.setyo.githubuser.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val searchQuery = MutableLiveData<String>()

    companion object {
        private const val TAG = "MainViewModel"
        private const val GITHUB_USERNAME = "arif"
    }

    init {
        findGithubUser(GITHUB_USERNAME)
    }

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
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}