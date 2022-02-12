package com.rino.githubusers.model

import java.util.*

data class GithubRepos(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val fork: Boolean,
    val url: String? = null,
    val forksUrl: String? = null,
    val createdAt: Date,
    val updatedAt: Date,
    val watchersCount: Long,
    val forksCount: Long
)