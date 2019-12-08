package com.example.android.githubaccountsearch.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.githubaccountsearch.Repository
import com.example.android.githubaccountsearch.enums.GitRequestStatus
import com.example.android.githubaccountsearch.models.Account
import com.example.android.githubaccountsearch.models.GitRepository
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


    fun getAccount(profileName: String) {

        viewModelScope.launch {
            val (account, accountStatus) = Repository.getAccount(profileName)

            if (accountStatus == GitRequestStatus.ERROR ) {
                _status.postValue(accountStatus)
            }
            else{
                if (account != null){
                    _account.postValue(account)

                    val (repos, reposStatus) = Repository.getAccountRepos(profileName)

                    _repos.postValue(repos)
                    _status.postValue(reposStatus)
                }
            }
        }
    }
}