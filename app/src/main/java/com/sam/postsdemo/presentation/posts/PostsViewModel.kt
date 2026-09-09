package com.sam.postsdemo.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sam.postsdemo.data.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {
    private val repository = PostRepository()
    private val _uiState = MutableStateFlow(PostsUiState())
    val uiState : StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    private fun loadPosts() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(isLoading = true)
            }
            val posts = repository.getPosts()
            _uiState.update {
                it.copy(posts = posts, isLoading = false)
            }

        }
    }
}