package com.rino.githubusers.model

data class GithubUser(
    val id: Long,
    val login: String,
    val avatarUrl: String? = null
)

