package com.example.android.githubaccountsearch.network

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val message: String, val throwable: Throwable?) : Result<T>()
}
