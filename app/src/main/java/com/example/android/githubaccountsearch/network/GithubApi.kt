package com.example.android.githubaccountsearch.network

import retrofit2.Retrofit

class GithubApi(retrofit: Retrofit) {
    val request: GithubApiRequests = retrofit.create(GithubApiRequests::class.java)
}