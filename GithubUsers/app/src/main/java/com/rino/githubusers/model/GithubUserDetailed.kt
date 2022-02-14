package com.rino.githubusers.model

import java.util.*

data class GithubUserDetailed (
    val login: String,
    val id: Long,
    val publicRepos: Long,
    val followers: Long,
    val following: Long,
    val createdAt: Date,
    val updatedAt: Date,
    val avatarUrl: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val reposUrl: String? = null,
    val name: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null
)