package com.example.android.githubaccountsearch.models

import com.squareup.moshi.Json
import java.util.Calendar

data class GitRepository(
    val name: String,

    val description: String?,

    @Json(name = "html_url")
    val url: String,

    val language: String?,

    @Json(name = "stargazers_count")
    val starsCount: Int = 0,

    @Json(name = "forks_count")
    val forksCount: Int = 0,

    val license: License?,

    @Json(name = "created_at")
    val creationYear: Calendar,

    @Json(name = "pushed_at")
    val lastPushYear: Calendar,

    val size: Int
)
