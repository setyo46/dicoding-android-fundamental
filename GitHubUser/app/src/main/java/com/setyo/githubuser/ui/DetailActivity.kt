package com.setyo.githubuser.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setyo.githubuser.R

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}