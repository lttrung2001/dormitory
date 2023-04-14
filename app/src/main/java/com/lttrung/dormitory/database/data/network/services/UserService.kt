package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @GET("api/student/info")
    fun fetchUserProfile(): Single<Response<UserProfile>>

    @POST("api/auth/change-password")
    fun changePassword(
        @Body body: Map<String, String>
    ): Single<Response<Boolean>>

    @GET("api/student/contract/")
    fun fetchRoomContract(): Single<Response<FetchRoomContractResponse>>

    @GET("api/student/contract/create/{roomId}")
    fun registerRoom(@Path("roomId") roomId: Int): Single<Response<Boolean>>
}