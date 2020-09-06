package com.example.android.githubaccountsearch.di

import com.example.android.githubaccountsearch.BuildConfig
import com.example.android.githubaccountsearch.Repository
import com.example.android.githubaccountsearch.adapters.CalendarAdapter
import com.example.android.githubaccountsearch.network.GithubApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

val networkModule = module {
    single { provideMoshi() }
    single { provideRetrofit(get(), BuildConfig.GIT_BASE_URL) }
    single { GithubApi(get()) }
    single { Repository(get()) }
}

fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Calendar::class.java, CalendarAdapter())
        .build()
}

fun provideRetrofit(moshi: Moshi, baseUrl: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(baseUrl)
        .build()
}