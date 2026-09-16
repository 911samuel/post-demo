package com.sam.postsdemo.di

import com.sam.postsdemo.BuildConfig
import com.sam.postsdemo.data.remote.SpaceflightNewsApi
import com.sam.postsdemo.data.repository.PostRepositoryImpl
import com.sam.postsdemo.domain.repository.PostRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {

    private val loggingInterceptor: HttpLoggingInterceptor by lazy {
        HttpLoggingInterceptor().apply {
            level = (if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
        }
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            // Required for Moshi to understand Kotlin data classes,
            // default values and non-null types via reflection.
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(SpaceflightNewsApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val api: SpaceflightNewsApi by lazy {
        retrofit.create(SpaceflightNewsApi::class.java)
    }

    val postRepository: PostRepository by lazy {
        PostRepositoryImpl(api)
    }
}