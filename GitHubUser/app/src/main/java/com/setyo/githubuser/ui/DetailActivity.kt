package com.setyo.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.setyo.githubuser.R
import com.setyo.githubuser.data.DetailUserResponse
import com.setyo.githubuser.databinding.ActivityDetailBinding
import com.setyo.githubuser.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel>()

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            title = getString(R.string.title_detail)
            setDisplayHomeAsUpEnabled(true)
        }

        val username = intent.getStringExtra(EXTRA_USER)
        detailViewModel.detailGithubUser(username)

        detailViewModel.listUserDetail.observe(this) { detailUser ->
            setUserData(detailUser)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

    }

    private fun setUserData(user: DetailUserResponse) {
        binding.apply {
            tvName.text = user.name
            tvUsername.text = user.login
            Glide.with(applicationContext)
                .load(user.avatarUrl)
                .error(R.drawable.baseline_person_24)
                .into(ivAvatar)
            tvFollowersCount.text = user.followers.toString()
            tvFollowingCount.text = user.following.toString()
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}