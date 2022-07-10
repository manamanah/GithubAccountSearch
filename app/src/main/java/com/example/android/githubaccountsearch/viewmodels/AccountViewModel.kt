package com.example.android.githubaccountsearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.githubaccountsearch.Repository
import com.example.android.githubaccountsearch.enums.GitRequestStatus
import com.example.android.githubaccountsearch.models.Account
import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.models.Language
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AccountViewModel : ViewModel(), KoinComponent {

    private val repository: Repository by inject()

    private val _repoStatus = MutableLiveData<GitRequestStatus>()
    val repoStatus : LiveData<GitRequestStatus>
        get() = _repoStatus

    private val _status = MutableLiveData<GitRequestStatus>()
    val status : LiveData<GitRequestStatus>
        get() = _status

    private val _account = MutableLiveData<Account>()
    val account : LiveData<Account>
        get() = _account

    private val _repos = MutableLiveData<List<GitRepository>>()
    val repos : LiveData<List<GitRepository>>
        get() = _repos

    private val _languages = MutableLiveData<MutableList<Language>>()
    val languages : LiveData<MutableList<Language>>
        get() = _languages


    fun getAccount(profileName: String) {
        viewModelScope.launch {
            val (account, accountStatus) = repository.getAccount(profileName)

            _status.value = accountStatus
            _account.value = account

            account?.let {
                val (repos, reposStatus) = repository.getAccountRepos(profileName)

                _repos.value = repos
                _repoStatus.value = reposStatus

                if (reposStatus == GitRequestStatus.SUCCESS) {
                    calculateLanguageUsage(repos)
                }
            }
        }
    }

    fun reset() {
        _status.value = GitRequestStatus.LOADING
        _repoStatus.value = GitRequestStatus.LOADING
        _account.value = null
        _repos.value = null
    }

    private fun calculateLanguageUsage(repoList: List<GitRepository>?) {
        if (repoList == null) return

        val map: MutableMap<String, Int> = mutableMapOf()
        var totalSize = 0.0

        // calculate sum of repo-sizes for each language
        repoList.filter { it.language != null }.groupBy { it.language }.forEach { (language, repoList) ->
            map[language!!] = repoList.sumOf { it.size }
            totalSize += map.getValue(language)
        }

        if (totalSize.equals(0.0)) return

        val list = mutableListOf<Language>()
        // calculate language fraction in relation to total-size
        map.forEach { (language, size) ->
            val tempLanguage = Language(language, "%.2f".format(size * 100 / totalSize))
            list.add(tempLanguage)
        }

        _languages.value = list
    }
}