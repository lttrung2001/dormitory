package com.lttrung.dormitory.database.data.network.login

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginNetwork {
    fun login(username: String, password: String): Single<LoginResponseBody>
    fun register(username: String, password: String): Single<String>
    fun forgotPassword(): Completable
    fun verifyCode(username: String, password: String, otp: String): Single<String>
    fun resetPassword(): Completable
}