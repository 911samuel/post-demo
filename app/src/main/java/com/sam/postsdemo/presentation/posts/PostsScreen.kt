package com.sam.postsdemo.presentation.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sam.postsdemo.data.Post

@Composable
fun PostsScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PostsContent(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun PostsContent(
    uiState: PostsUiState,
    modifier: Modifier = Modifier
) {

    Box(modifier = modifier.fillMaxSize()) {
        if (uiState.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = uiState.posts,
                    key = { it.id }

                ) { post ->
                    PostItem(post = post    )
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PostsContentPreview() {
    PostsContent(
        uiState = PostsUiState(
            posts = listOf(
                Post(1, "First post title", "https://picsum.photos/id/1015/600/400"),
                Post(2, "Second post title", "https://picsum.photos/id/1016/600/400")
            ),
            isLoading = false
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PostsContentLoadingPreview() {
    PostsContent(uiState = PostsUiState(isLoading = true))
}
