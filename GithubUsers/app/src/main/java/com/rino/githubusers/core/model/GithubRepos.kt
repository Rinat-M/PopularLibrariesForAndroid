package com.rino.githubusers.core.model

import java.util.*

data class GithubRepos(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val fork: Boolean,
    val url: String,
    val forksUrl: String? = null,
    val createdAt: Date,
    val updatedAt: Date,
    val watchersCount: Long,
    val forksCount: Long
)