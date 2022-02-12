package com.rino.githubusers.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GithubUserEntity(
    @PrimaryKey val id: Long,
    val login: String,
    val reposUrl: String,
    val publicRepos: Long,
    val followers: Long,
    val following: Long,
    val avatarUrl: String? = null,
    val name: String? = null,
    val location: String? = null,
    val createdAt: Date? = null
)