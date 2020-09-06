package com.example.android.githubaccountsearch.network

import com.example.android.githubaccountsearch.models.Account
import com.example.android.githubaccountsearch.models.GitRepository
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiRequests {
    @GET("users/{username}")
    fun getAccountAsync(@Path("username")username: String) : Deferred<Account>

    @GET("users/{username}/repos")
    fun getAccountsReposAsync(@Path("username")username: String) : Deferred<List<GitRepository>>
}