package com.rino.githubusers.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class CachedImage(
    @PrimaryKey val url: String,
    val localPath: String
) {
    val isEmpty get() = url.isBlank() && localPath.isBlank()
}