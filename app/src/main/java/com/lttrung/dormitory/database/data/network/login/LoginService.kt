package com.lttrung.dormitory.database.data.network.login

import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {
    @FormUrlEncoded
    @POST("/login")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Single<Response<LoginResponseBody>>
}