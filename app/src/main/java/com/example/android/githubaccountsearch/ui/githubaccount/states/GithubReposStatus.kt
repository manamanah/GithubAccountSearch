package com.example.android.githubaccountsearch.ui.githubaccount.states

import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.models.Language

sealed class GithubReposStatus {
    object Loading : GithubReposStatus()
    object SuccessWithoutRepository : GithubReposStatus()
    data class Success(
        val reposList: List<GitRepository>,
        val languages: List<Language>
    ) : GithubReposStatus()

    object Error : GithubReposStatus()
}
