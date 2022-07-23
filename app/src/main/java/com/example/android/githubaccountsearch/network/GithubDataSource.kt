package com.example.android.githubaccountsearch.network

import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.models.GithubAccount
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubDataSource {
    @GET("users/{username}")
    suspend fun getAccount(@Path("username") username: String): GithubAccount

    @GET("users/{username}/repos")
    suspend fun getAccountsRepos(@Path("username") username: String): List<GitRepository>
}
