package com.sam.postsdemo.data.repository

import com.sam.postsdemo.data.mapper.toPosts
import com.sam.postsdemo.data.remote.SpaceflightNewsApi
import com.sam.postsdemo.domain.model.Post
import com.sam.postsdemo.domain.repository.PostRepository
import com.sam.postsdemo.domain.utils.Resource
import com.sam.postsdemo.domain.utils.Resource.Error
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val api: SpaceflightNewsApi
) : PostRepository {

    override suspend fun getPosts(): Resource<List<Post>> {
        return try {
            val response = api.getArticles()
            Resource.Success(response.results.toPosts())
        } catch (e: IOException) {
            Error("No internet connection. Check your network and try again.")
        } catch (e: HttpException) {
            Error("Server error (${e.code()}). Please try again later.")
        } catch (e: Exception) {
            Error(e.message ?: "Something went wrong.")
        }
    }
}