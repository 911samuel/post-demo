package com.sam.postsdemo.data.remote

import com.sam.postsdemo.data.dto.ArticlesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SpaceflightNewsApi {

    @GET("v4/articles/")
    suspend fun getArticles(
        @Query("limit") limit: Int = 20,
        // Minus sign = descending, so newest articles first.
        @Query("ordering") ordering: String = "-published_at"
    ): ArticlesResponseDto

    companion object {
        const val BASE_URL = "https://api.spaceflightnewsapi.net/"
    }
}