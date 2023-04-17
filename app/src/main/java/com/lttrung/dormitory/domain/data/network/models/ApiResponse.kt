package com.lttrung.dormitory.domain.data.network.models

sealed interface ApiResponse<T> {
    class Success<T>(val data: T): ApiResponse<T>
    class Error<T>(val message: String = "Unknown error.", val data: T? = null): ApiResponse<T>
}