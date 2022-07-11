package com.example.android.githubaccountsearch.ui.githubaccount.states

import com.example.android.githubaccountsearch.models.GithubAccount

sealed class GithubAccountStatus {
    object Loading : GithubAccountStatus()
    data class Success(val account: GithubAccount) : GithubAccountStatus()
    object Error : GithubAccountStatus()
}