package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {
    @POST("$PATH/signin")
    fun login(
        @Body body: Map<String, String>
    ): Single<Response<LoginResponse>>

    @FormUrlEncoded
    @POST("$PATH/signup")
    fun register(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<Response<String>>

    @FormUrlEncoded
    @POST("$PATH/two-factor-auth")
    fun verifyCode(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("otpCode") otp: String
    ): Single<Response<String>>

    @GET("$PATH/forgot-password/{username}")
    fun forgotPassword(@Path("username") username: String): Single<Response<String>>

    companion object {
        private const val PATH = "api/auth"
    }
}