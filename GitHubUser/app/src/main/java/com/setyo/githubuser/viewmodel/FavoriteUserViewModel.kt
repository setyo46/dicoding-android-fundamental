package com.setyo.githubuser.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.database.FavoriteUser
import com.setyo.githubuser.repository.FavoriteUserRepository

class FavoriteUserViewModel(application: Application): ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository= FavoriteUserRepository(application)

    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavoriteUser()

}