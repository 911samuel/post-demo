package com.sam.postsdemo.data.dto

import com.squareup.moshi.Json

data class ArticleDto(
    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "image_url")
    val imageUrl: String?,

    @Json(name = "news_site")
    val newsSite: String?,

    @Json(name = "summary")
    val summary: String?,

    @Json(name = "published_at")
    val publishedAt: String?
)