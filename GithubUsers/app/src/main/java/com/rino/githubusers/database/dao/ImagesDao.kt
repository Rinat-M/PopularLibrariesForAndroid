package com.rino.githubusers.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rino.githubusers.database.entity.CachedImage
import io.reactivex.rxjava3.core.Single

@Dao
interface ImagesDao {

    @Insert
    fun insert(image: CachedImage)

    @Query("SELECT * FROM CachedImage WHERE url=:url")
    fun getCachedImageByUrl(url: String): CachedImage?

}