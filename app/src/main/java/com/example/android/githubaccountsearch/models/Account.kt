package com.example.android.githubaccountsearch.models

import com.squareup.moshi.Json

data class Account(
    @Json(name = "login")
    val profileName: String,

    @Json(name = "blog")
    val website: String,

    @Json(name = "avatar_url")
    val avatar: String
)