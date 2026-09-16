package com.sam.postsdemo.data.dto

import com.squareup.moshi.Json

data class ArticlesResponseDto(
    @Json(name = "count")
    val count: Int,

    @Json(name = "next")
    val next: String?,

    @Json(name = "previous")
    val previous: String?,

    @Json(name = "results")
    val results: List<ArticleDto>
)