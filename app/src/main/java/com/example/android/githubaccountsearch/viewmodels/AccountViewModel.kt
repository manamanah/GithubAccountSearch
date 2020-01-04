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


class AccountViewModel : ViewModel(){

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
            val (account, accountStatus) = Repository.getAccount(profileName)

            if (accountStatus == GitRequestStatus.ERROR) {
                _status.value = accountStatus
            }
            else{
                _account.value = account

                if (account != null){
                    val (repos, reposStatus) = Repository.getAccountRepos(profileName)

                    _repos.value = repos
                    _status.value = reposStatus

                    if (reposStatus == GitRequestStatus.SUCCESS){
                        calculateLanguageUsage(repos)
                    }
                }
            }
        }
    }

    fun reset(){
        _status.value = GitRequestStatus.LOADING
        _account.value = null
        _repos.value = null
    }

    private fun calculateLanguageUsage(repoList: List<GitRepository>?){
        if (repoList == null)
            return

        val map: MutableMap<String, Int> = mutableMapOf()
        var totalSize = 0.0

        // calculate sum of repo-sizes for each language
        repoList.filter { it.language != null }.groupBy { it.language }.forEach { (language, repoList) ->
            map[language!!] = repoList.sumBy { it.size }
            totalSize += map.getValue(language)
        }

        if (totalSize.equals(0.0))
            return

        val list = mutableListOf<Language>()
        // calculate language fraction in relation to total-size
        map.forEach { (language, size) ->
            val tempLanguage = Language(language, "%.2f".format(size * 100 / totalSize))
            list.add(tempLanguage)
        }

        _languages.value = list
    }
}