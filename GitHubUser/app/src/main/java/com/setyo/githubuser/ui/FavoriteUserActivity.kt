package com.setyo.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.githubuser.R
import com.setyo.githubuser.adapter.ListUsersAdapter
import com.setyo.githubuser.data.GithubUser
import com.setyo.githubuser.databinding.ActivityFavoriteUserBinding
import com.setyo.githubuser.helper.ViewModelFactory
import com.setyo.githubuser.viewmodel.FavoriteUserViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private val favoriteUserViewModel by viewModels<FavoriteUserViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private lateinit var adapter: ListUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = getString(R.string.title_favorite)
            setDisplayHomeAsUpEnabled(true)
        }

        adapter = ListUsersAdapter(emptyList())
        binding.rvGithubFavorite.adapter = adapter
        showRecyclerView()

        favoriteUserViewModel.getAllFavoriteUser().observe(this) { favoriteUsers ->
            val users = favoriteUsers.map { favoriteUser ->
                GithubUser(
                    login = favoriteUser.username,
                    avatarUrl = favoriteUser.avatarUrl,
                )
            }
            val adapter = ListUsersAdapter(users)
            adapter.setListNotes(favoriteUsers)
            binding.rvGithubFavorite.adapter = adapter
        }
    }

    private fun showRecyclerView() {
        binding.rvGithubFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            setHasFixedSize(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }
}

