package com.rino.githubusers.core.model

import com.rino.githubusers.database.entity.GithubRepoEntity
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
    val updatedAt: Date? = null,
    val watchersCount: Long,
    val forksCount: Long,
    val owner: GithubUser
) {
    val dbModel
        get() = GithubRepoEntity(
            id = id,
            name = name,
            fullName = fullName,
            createdAt = createdAt,
            isPrivate = private,
            isFork = fork,
            watchersCount = watchersCount,
            forksCount = forksCount,
            userId = owner.id,
            url = url
        )
}