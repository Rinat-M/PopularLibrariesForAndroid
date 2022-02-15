package com.rino.githubusers.di.modules

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rino.githubusers.BuildConfig
import com.rino.githubusers.network.GithubApiService
import com.rino.githubusers.network.NetworkStatus
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGithubApiService(retrofit: Retrofit): GithubApiService {
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient {
        return OkHttpClient.Builder().apply {
            httpLoggingInterceptor?.let { this.addInterceptor(it) }
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.GITHUB_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }
        } else {
            null
        }
    }

    @Provides
    @Singleton
    fun providesNetworkStatus(context: Context): NetworkStatus {
        return NetworkStatus(context)
    }

}