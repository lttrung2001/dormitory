package com.lttrung.dormitory.domain.data.network.services

import com.lttrung.dormitory.domain.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET("api/student/info")
    fun fetchUserProfile(): Single<Response<UserProfile>>

    @POST("api/auth/change-password")
    fun changePassword(
        @Body body: Map<String, String>
    ): Single<Response<Unit>>
}