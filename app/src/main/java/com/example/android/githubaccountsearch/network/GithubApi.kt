package com.example.android.githubaccountsearch.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class GithubApi(base_url: String) {

    private val moshi = Moshi.Builder()
                                    .add(KotlinJsonAdapterFactory())
                                    .build()

    private val retrofit = Retrofit.Builder()
                                            .addConverterFactory(MoshiConverterFactory.create(moshi))
                                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                            .baseUrl(base_url)
                                            .build()

    val retrofitService: GithubApiService = retrofit.create(GithubApiService::class.java)
}