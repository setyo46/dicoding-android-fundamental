package com.setyo.githubuser.ui

import android.app.SearchManager
import android.content.Context
import com.setyo.githubuser.viewmodel.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setyo.githubuser.R
import com.setyo.githubuser.adapter.ListUsersAdapter
import com.setyo.githubuser.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvGithubUser.adapter = ListUsersAdapter(emptyList())
        showRecyclerView()

        mainViewModel.listUser.observe(this) {
            binding.rvGithubUser.adapter = ListUsersAdapter(it)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        mainViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { textToast ->
                Toast.makeText(
                    this, textToast, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun showRecyclerView() {
        binding.rvGithubUser.apply {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)

        mainViewModel.searchQuery.observe(this) {query ->
            searchView.setQuery(query.toString(),false)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mainViewModel.findGithubUser(query)
                }
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.searchQuery.postValue(newText)
                return false
            }
        })
        return true
    }

}