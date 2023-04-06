package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.UserProfileResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface UserService {
    @GET("get-user-profile")
    fun fetchUserProfile(): Single<Response<UserProfileResponse>>
}