package com.example.android.githubaccountsearch.ui.githubaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.githubaccountsearch.network.GithubRepository

class GithubAccountViewModelProvider(
    private val profileName: String,
    private val githubRepository: GithubRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GithubAccountViewModel::class.java)) {
            GithubAccountViewModel(
                profileName = profileName,
                githubRepository = githubRepository
            ) as T
        } else {
            throw IllegalArgumentException("Cannot create instance of ${GithubAccountViewModel::class.java.simpleName} with parameter $githubRepository")
        }
    }
}
