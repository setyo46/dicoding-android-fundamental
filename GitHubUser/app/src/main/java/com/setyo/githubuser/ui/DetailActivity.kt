package com.setyo.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.setyo.githubuser.R
import com.setyo.githubuser.adapter.SelectionsPagerAdapter
import com.setyo.githubuser.data.DetailUserResponse
import com.setyo.githubuser.database.FavoriteUser
import com.setyo.githubuser.databinding.ActivityDetailBinding
import com.setyo.githubuser.helper.ViewModelFactory
import com.setyo.githubuser.viewmodel.DetailViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private val selectionsPagerAdapter = SelectionsPagerAdapter(this)

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
        if (username != null) {
            selectionsPagerAdapter.username = username
        }

        detailViewModel.listUserDetail.observe(this) { detailUser ->
            setUserData(detailUser)
        }

        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        detailViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { textToast ->
                Toast.makeText(
                    this, textToast, Toast.LENGTH_SHORT
                ).show()
            }
        }

        if (username != null) {
            detailViewModel.getFavoriteUserByUsername(username).observe(this) { favoriteUsername ->
                if (favoriteUsername != null) {
                    binding.fabAdd.setImageResource(R.drawable.baseline_favorite_24)
                    binding.fabAdd.setOnClickListener {
                        detailViewModel.delete(favoriteUsername)
                        Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    binding.fabAdd.setImageResource(R.drawable.baseline_favorite_border_24)
                    binding.fabAdd.setOnClickListener {
                        val usernameFavorite = detailViewModel.listUserDetail.value?.login
                        val avatarUrl = detailViewModel.listUserDetail.value?.avatarUrl
                        if (!usernameFavorite.isNullOrEmpty() && !avatarUrl.isNullOrEmpty()) {
                            val favoriteUser = FavoriteUser(
                                username = usernameFavorite,
                                avatarUrl = avatarUrl
                            )
                            detailViewModel.insert(favoriteUser)
                            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "Failed to add to favorite", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        binding.viewPager.adapter = selectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        private val TAB_TITLES = intArrayOf(
            R.string.followers,
            R.string.following
        )
    }

}