package com.sam.postsdemo.ui.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.sam.postsdemo.di.ServiceLocator
import com.sam.postsdemo.domain.repository.PostRepository
import com.sam.postsdemo.domain.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostsViewModel(
    private val repository: PostRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PostsUiState(isLoading = true))
    val uiState: StateFlow<PostsUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun loadPosts() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }
        fetch(isRefresh = false)
    }

    fun refresh() {
        _uiState.update { it.copy(isRefreshing = true, errorMessage = null) }
        fetch(isRefresh = true)
    }

    private fun fetch(isRefresh: Boolean) {
        viewModelScope.launch {
            when (val result = repository.getPosts()) {
                is Resource.Success -> _uiState.update { state ->
                    state.copy(
                        posts = result.data,
                        isLoading = false,
                        isRefreshing = false,
                        errorMessage = null
                    )
                }

                is Resource.Error -> _uiState.update { state ->
                    state.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }

    fun onErrorShown() {
        _uiState.update { it.copy(errorMessage = null) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                PostsViewModel(
                    repository = ServiceLocator.postRepository
                )
            }
        }
    }
}
