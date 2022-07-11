package com.example.android.githubaccountsearch.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import java.util.concurrent.TimeUnit

const val READ_TIME_OUT = 30L
const val CONNECT_TIME_OUT = 30L

fun getBaseOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BASIC)
                })
            }
        }
        .build()