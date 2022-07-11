package com.example.android.githubaccountsearch.di

import com.example.android.githubaccountsearch.R
import com.example.android.githubaccountsearch.network.GithubRepository
import com.example.android.githubaccountsearch.ui.githubaccount.GithubAccountViewModel
import com.example.android.githubaccountsearch.utils.getBaseOkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single {
        GithubRepository(
            baseUrl = androidContext().resources.getString(R.string.github_base_Url),
            okHttpClient = getBaseOkHttpClient()
        )
    }
}

val uiModule = module {
    viewModel { (profileName: String) ->
        GithubAccountViewModel(
            profileName = profileName,
            githubRepository = get()
        )
    }
}
