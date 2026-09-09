package com.sam.postsdemo.presentation.posts

import com.sam.postsdemo.data.Post

data class PostsUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
)