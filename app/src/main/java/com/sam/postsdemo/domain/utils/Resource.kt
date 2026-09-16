package com.sam.postsdemo.domain.utils

sealed interface Resource<out T> {

    data class Success<out T>(val data: T) : Resource<T>

    data class Error(val message: String) : Resource<Nothing>
}