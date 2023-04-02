package com.setyo.githubuser.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.setyo.githubuser.database.FavoriteUser
import com.setyo.githubuser.repository.FavoriteUserRepository

class FavoriteUserAddUpdateViewModel(application: Application): ViewModel() {
    private val mFavoriteUserRepository: FavoriteUserRepository= FavoriteUserRepository(application)

    fun insert(favoriteUser: FavoriteUser) {
        mFavoriteUserRepository.insert(favoriteUser)
    }

}