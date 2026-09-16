package com.sam.postsdemo.domain.repository

import com.sam.postsdemo.domain.model.Post
import com.sam.postsdemo.domain.utils.Resource

interface PostRepository {

    suspend fun getPosts(): Resource<List<Post>>
}