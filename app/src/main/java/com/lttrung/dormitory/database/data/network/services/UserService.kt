package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @GET(PATH)
    fun fetchUserProfile(): Single<Response<UserProfile>>

    @FormUrlEncoded
    @POST(PATH)
    fun changePassword(
        @Field("oldPassword") oldPassword: String,
        @Field("newPassword") newPassword: String
    ): Single<Response<Boolean>>

    @GET(PATH)
    fun fetchRoomContract(): Single<Response<FetchRoomContractResponse>>

    companion object {
        private const val PATH = "api/student/info"
    }
}