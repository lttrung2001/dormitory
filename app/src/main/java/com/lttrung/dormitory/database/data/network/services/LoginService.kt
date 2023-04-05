package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.LoginResponse
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
    ): Single<Response<LoginResponse>>

    @FormUrlEncoded
    @POST("/sign-up")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Response<String>>

    @FormUrlEncoded
    @POST("verify-code")
    fun verifyCode(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("OTP") otp: String
    ): Single<Response<String>>
}