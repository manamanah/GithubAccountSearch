package com.example.android.githubaccountsearch.ui.githubaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.models.Language
import com.example.android.githubaccountsearch.network.GithubRepository
import com.example.android.githubaccountsearch.network.Result
import com.example.android.githubaccountsearch.ui.githubaccount.states.GithubAccountStatus
import com.example.android.githubaccountsearch.ui.githubaccount.states.GithubReposStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class GithubAccountViewModel(
    profileName: String,
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _accountStatus = MutableStateFlow<GithubAccountStatus>(GithubAccountStatus.Loading)
    val accountStatus: StateFlow<GithubAccountStatus>
        get() = _accountStatus

    private val _reposStatus = MutableStateFlow<GithubReposStatus>(GithubReposStatus.Loading)
    val githubReposStatus: StateFlow<GithubReposStatus>
        get() = _reposStatus

    init {
        viewModelScope.launch {
            getGithubAccountForUser(profileName)
            getGithubAccountReposForUser(profileName)
        }
    }

    private suspend fun getGithubAccountForUser(profileName: String) {
        Timber.d("get github user account for $profileName")

        when (val result = githubRepository.getAccount(profileName)) {
            is Result.Success -> {
                _accountStatus.value = GithubAccountStatus.Success(account = result.data)
            }
            is Result.Error -> {
                Timber.e(t = result.throwable, message = result.message)
                _accountStatus.value = GithubAccountStatus.Error
            }
        }
    }

    private suspend fun getGithubAccountReposForUser(profileName: String) {
        Timber.d("get github user account repos for $profileName")

        when (val result = githubRepository.getAccountRepos(profileName)) {
            is Result.Success -> {
                if (result.data.isEmpty()) {
                    GithubReposStatus.SuccessWithoutRepository
                } else {
                    val languageList = calculateLanguageUsage(result.data)
                    _reposStatus.value =
                        GithubReposStatus.Success(
                            reposList = result.data,
                            languages = languageList
                        )
                }
            }
            is Result.Error -> {
                Timber.e(t = result.throwable, message = result.message)
                _reposStatus.value = GithubReposStatus.Error
            }
        }
    }

    private fun calculateLanguageUsage(repoList: List<GitRepository>): List<Language> {
        Timber.d("calculate language distribution for ${repoList.size} repos")
        if (repoList.isEmpty()) return listOf()
        val map: MutableMap<String, Int> = mutableMapOf()
        var totalSize = 0.0

        // calculate sum of repo-sizes for each language
        repoList
            .groupBy { it.language }
            .forEach { (language, languageRepoList) ->
                if (language.isNullOrEmpty()) return@forEach
                map[language] = languageRepoList.sumOf { it.size }
                totalSize += map.getValue(language)
            }

        if (totalSize.equals(0.0)) return listOf()

        val list = mutableListOf<Language>()
        // calculate language percentage in relation to total-size
        map.forEach { (language, size) ->
            val tempLanguage = Language(language, "%.2f".format(size * 100 / totalSize))
            list.add(tempLanguage)
        }

        return list
    }
}
