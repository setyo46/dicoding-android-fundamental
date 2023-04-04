package com.setyo.githubuser.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.adapter.Event
import com.setyo.githubuser.api.ApiConfig
import com.setyo.githubuser.data.DetailUserResponse
import com.setyo.githubuser.database.FavoriteUser
import com.setyo.githubuser.repository.FavoriteUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {

    private val _listUserDetail = MutableLiveData<DetailUserResponse>()
    val listUserDetail: LiveData<DetailUserResponse> = _listUserDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _textToast = MutableLiveData<Event<String>>()
    val textToast: LiveData<Event<String>> = _textToast

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> = mFavoriteUserRepository.getFavoriteUserByUsername(username)

    fun detailGithubUser(username: String?) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listUserDetail.value = response.body()
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


    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun delete(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.delete(favoriteUser)
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}

