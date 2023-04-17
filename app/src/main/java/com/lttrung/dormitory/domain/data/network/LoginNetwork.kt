package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.LoginResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginNetwork {
    fun login(username: String, password: String): Single<LoginResponse>
    fun register(username: String, password: String): Single<Unit>
    fun forgotPassword(username: String): Single<Unit>
    fun verifyCode(username: String, password: String, otp: String): Single<Unit>
}