package com.rino.githubusers.di.modules

import android.widget.ImageView
import com.rino.githubusers.database.dao.ImagesDao
import com.rino.githubusers.ui.base.GlideImageCacheLoader
import com.rino.githubusers.ui.base.ImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Provides
    @Singleton
    fun providesGlideImageCacheLoader(imagesDao: ImagesDao): ImageLoader<ImageView> {
        return GlideImageCacheLoader(imagesDao)
    }

}