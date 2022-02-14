package com.rino.githubusers.ui.base

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rino.githubusers.database.dao.ImagesDao
import com.rino.githubusers.database.entity.CachedImage
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.util.*

class GlideImageCacheLoader(private val imagesDao: ImagesDao) : ImageLoader<ImageView> {

    companion object {
        private const val TAG = "GlideImageCacheLoader"
    }

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container)
            .asBitmap()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .load(url)
            .addListener(object : RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    val single = Single.fromCallable {
                        val imageUrl = model.toString()
                        imagesDao.getCachedImageByUrl(imageUrl) ?: CachedImage("", "")
                    }

                    single.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { cachedImage ->
                                val bitmap = BitmapFactory.decodeFile(cachedImage.localPath)
                                container.setImageBitmap(bitmap)

                                if (!cachedImage.isEmpty) {
                                    Log.d(TAG, "CachedImage loaded from database!")
                                } else {
                                    Log.d(TAG, "CachedImage info doesn't have in database!")
                                }
                            },
                            {
                                Log.e(TAG, it.stackTraceToString())
                            }
                        )

                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let { bitmap ->
                        val single = Single.fromCallable {
                            val imageUrl = model.toString()
                            val cachedImage = imagesDao.getCachedImageByUrl(imageUrl)

                            if (cachedImage == null) {
                                val storageDir: File? =
                                    container.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                                val fileName = UUID.randomUUID().toString()
                                val outputFile = File(storageDir!!.absolutePath, "$fileName.png")

                                FileOutputStream(outputFile).use {
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                                }

                                val newCachedImage = CachedImage(imageUrl, outputFile.absolutePath)
                                imagesDao.insert(newCachedImage)
                            } else {
                                val outputFile = File(cachedImage.localPath)

                                FileOutputStream(outputFile).use {
                                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
                                }
                            }
                        }

                        single.subscribeOn(Schedulers.io())
                            .subscribe(
                                {
                                    Log.d(TAG, "CachedImage saved to database successfully!")
                                },
                                {
                                    Log.e(TAG, it.stackTraceToString())
                                }
                            )

                        Log.d(
                            TAG,
                            "Successful loading. allocationByteCount = ${bitmap.allocationByteCount / 1024}Kb"
                        )
                    }

                    return false
                }

            })
            .into(container)
    }

}

