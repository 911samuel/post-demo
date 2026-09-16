package com.sam.postsdemo.domain.model

data class Post(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val newsSite: String
)