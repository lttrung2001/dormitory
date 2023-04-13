package com.lttrung.dormitory.database.data.network

import com.lttrung.dormitory.database.data.network.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginNetwork {
    fun login(username: String, password: String): Single<LoginResponse>
    fun register(username: String, password: String): Single<String>
    fun forgotPassword(username: String): Single<String>
    fun verifyCode(username: String, password: String, otp: String): Single<String>
}