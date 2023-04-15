package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {
    @POST("$PATH/signin")
    fun login(
        @Body body: Map<String, String>
    ): Single<Response<LoginResponse>>

    @POST("$PATH/signup")
    fun register(
        @Body body: Map<String, String>
    ): Single<Response<String>>

    @POST("$PATH/two-factor-auth")
    fun verifyCode(
        @Body body: Map<String, String>
    ): Single<Response<String>>

    @GET("$PATH/forgot-password/{username}")
    fun forgotPassword(@Path("username") username: String): Single<Response<String>>

    companion object {
        private const val PATH = "api/auth"
    }
}