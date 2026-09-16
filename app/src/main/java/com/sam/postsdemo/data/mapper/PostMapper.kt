package com.sam.postsdemo.data.mapper

import com.sam.postsdemo.data.dto.ArticleDto
import com.sam.postsdemo.domain.model.Post

fun ArticleDto.toPost(): Post {
    return Post(
        id = id,
        title = title,
        imageUrl = imageUrl,
        newsSite = newsSite ?: "Unknown source"
    )
}

fun List<ArticleDto>.toPosts(): List<Post> = map { it.toPost() }