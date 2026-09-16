package com.sam.postsdemo.ui.posts

import com.sam.postsdemo.domain.model.Post

data class PostsUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null
)