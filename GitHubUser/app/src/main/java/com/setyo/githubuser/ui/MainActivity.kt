package com.setyo.githubuser.ui

import MainViewModel
import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.setyo.githubuser.R
import com.setyo.githubuser.adapter.ListUsersAdapter
import com.setyo.githubuser.api.ApiConfig
import com.setyo.githubuser.data.GithubResponse
import com.setyo.githubuser.data.GithubUser
import com.setyo.githubuser.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var userQuery: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.svUser.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    userQuery = query.toString()
                    clearFocus()
                    val getData = mainViewModel.findGithubUser(userQuery)
                    if (userQuery.isEmpty() || getData.equals(null)) {
                        binding.rvUser.adapter = ListUsersAdapter(emptyList())
                        showImage(true)
                    } else {
                        showImage(true)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    userQuery = newText.toString()
                    if (userQuery.isEmpty()) {
                        binding.rvUser.adapter = ListUsersAdapter(emptyList())
                        showImage(true)
                    } else {
                        binding.rvUser.adapter = ListUsersAdapter(emptyList())
                        showImage(false)
                    }
                    return true
                }

            })
        }

        showRecyclerList()
        mainViewModel.listUser.observe(this) {listGithubUser ->
            binding.rvUser.adapter = ListUsersAdapter(listGithubUser)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }
    }

    private fun showRecyclerList() {
        binding.rvUser.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun showImage(isImageVisible: Boolean) {

    }

}