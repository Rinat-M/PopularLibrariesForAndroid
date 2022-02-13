package com.rino.githubusers.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.rino.githubusers.core.model.GithubRepos
import com.rino.githubusers.core.model.GithubUser
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GithubUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GithubRepoEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val userId: Long,
    val fullName: String,
    val createdAt: Date,
    val isPrivate: Boolean,
    val isFork: Boolean,
    val watchersCount: Long,
    val forksCount: Long,
    val url: String
) {
    val coreModel
        get() = GithubRepos(
            id = id,
            name = name,
            fullName = fullName,
            private = isPrivate,
            fork = isFork,
            watchersCount = watchersCount,
            forksCount = forksCount,
            createdAt = createdAt,
            owner = GithubUser(userId, "", ""),
            url = url
        )
}