package com.example.android.githubaccountsearch

import android.util.Log
import com.example.android.githubaccountsearch.enums.GitRequestStatus
import com.example.android.githubaccountsearch.models.Account
import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.network.GithubApi

class Repository(private val githubApi: GithubApi) {

    suspend fun getAccount(profileName: String): Pair<Account?, GitRequestStatus> {
        val deferredAccount = githubApi.request.getAccountAsync(profileName)

        return try {
            val account = deferredAccount.await()
            Pair(account, GitRequestStatus.SUCCESS)
        } catch (e: Exception){
            Log.e(this.javaClass.simpleName, "getAccount with $profileName - Error: ${e.message}")
            Pair(null, GitRequestStatus.ERROR)
        }
    }

    suspend fun getAccountRepos(profileName: String): Pair<List<GitRepository>?, GitRequestStatus> {
        val deferredAccountRepos = githubApi.request.getAccountsReposAsync(profileName)

        return try {
            val repos = deferredAccountRepos.await()
            Pair(repos, GitRequestStatus.SUCCESS)
        } catch (e: Exception){
            Pair(null, GitRequestStatus.ERROR)
        }
    }
}