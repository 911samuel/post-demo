package com.sam.postsdemo.data

import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

class PostRepository {
    private val posts = listOf(
        Post(
            id = 1,
            title = "Thinking in Compose: describing UI instead of mutating it",
            imageUrl = "https://picsum.photos/id/1015/600/400"
        ),
        Post(
            id = 2,
            title = "Row, Column and Box: the three layouts you'll use every day",
            imageUrl = "https://picsum.photos/id/1016/600/400"
        ),
        Post(
            id = 3,
            title = "Modifier order matters, and here's the example that proves it",
            imageUrl = "https://picsum.photos/id/1018/600/400"
        ),
        Post(
            id = 4,
            title = "remember vs rememberSaveable: surviving a screen rotation",
            imageUrl = "https://picsum.photos/id/1019/600/400"
        ),
        Post(
            id = 5,
            title = "State hoisting: why your composables should be stateless",
            imageUrl = "https://picsum.photos/id/1020/600/400"
        ),
        Post(
            id = 6,
            title = "Cold flows are lazy: nothing runs until you collect",
            imageUrl = "https://picsum.photos/id/1021/600/400"
        ),
        Post(
            id = 7,
            title = "StateFlow in a ViewModel: one source of truth for the screen",
            imageUrl = "https://picsum.photos/id/1022/600/400"
        ),
        Post(
            id = 8,
            title = "Loading images from a URL with Coil and AsyncImage",
            imageUrl = "https://picsum.photos/id/1024/600/400"
        )
    )

    suspend fun getPosts() : List<Post> {
        delay(1000.milliseconds)
        return posts
    }
}