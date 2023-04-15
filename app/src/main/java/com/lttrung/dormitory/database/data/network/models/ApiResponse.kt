package com.lttrung.dormitory.database.data.network.models

import com.lttrung.dormitory.utils.Resource

sealed interface ApiResponse<T> {
    class Success<T>(val data: T): ApiResponse<T>
    class Error<T>(val message: String = "Unknown error.", val data: T? = null): ApiResponse<T>
}