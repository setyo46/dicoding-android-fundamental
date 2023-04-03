package com.setyo.githubuser.helper

import com.setyo.githubuser.database.FavoriteUser
import androidx.recyclerview.widget.DiffUtil

class FavoriteUserDiffCallback(private val mOldFavoriteUserList: List<FavoriteUser>, private val mNewFavoriteUserList: List<FavoriteUser>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewFavoriteUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteUserList[oldItemPosition].username == mNewFavoriteUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldFavoriteUserList[oldItemPosition]
        val newEmployee = mNewFavoriteUserList[oldItemPosition]
        return oldEmployee.username == newEmployee.username && oldEmployee.avatarUrl == newEmployee.avatarUrl
    }


}