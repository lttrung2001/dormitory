package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET(PATH)
    fun fetchUserProfile(): Single<Response<UserProfile>>

    companion object {
        private const val PATH = "api/student/info"
    }
}