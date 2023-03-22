package com.setyo.githubuser.api

import com.setyo.githubuser.BuildConfig
import com.setyo.githubuser.data.DetailUserResponse
import com.setyo.githubuser.data.GithubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    fun getListUser(@Query("q") query: String?
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String?
    ): Call<DetailUserResponse>


}