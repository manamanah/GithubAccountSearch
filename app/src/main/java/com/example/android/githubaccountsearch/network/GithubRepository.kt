package com.example.android.githubaccountsearch.network

import android.util.Log
import com.example.android.githubaccountsearch.models.GitRepository
import com.example.android.githubaccountsearch.models.GithubAccount
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Repository for getting github data and returning according
 * Result.Success or Result.Error
 *
 * for now very basic exception handling
 */
class GithubRepository(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private val githubDataSource = getDataSource(baseUrl, okHttpClient)

    suspend fun getAccount(profileName: String): Result<GithubAccount> =
        withContext(ioDispatcher) {
            try {
                val account = githubDataSource.getAccount(profileName)
                Result.Success(account)
            } catch (e: Exception) {
                Log.e(
                    this.javaClass.simpleName,
                    "getAccount with $profileName - Error: ${e.message}"
                )
                Result.Error(e.message.orEmpty(), e.cause)
            }
        }

    suspend fun getAccountRepos(profileName: String): Result<List<GitRepository>> =
        withContext(ioDispatcher) {
            try {
                val repos = githubDataSource.getAccountsRepos(profileName)
                Result.Success(repos)
            } catch (e: Exception) {
                Log.e(
                    this.javaClass.simpleName,
                    "getAccount repos with $profileName - Error: ${e.message}"
                )
                Result.Error(e.message.orEmpty(), e.cause)
            }
        }

    private fun getDataSource(baseUrl: String, okHttpClient: OkHttpClient): GithubDataSource {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Calendar::class.java, CalendarAdapter())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GithubDataSource::class.java)
    }
}