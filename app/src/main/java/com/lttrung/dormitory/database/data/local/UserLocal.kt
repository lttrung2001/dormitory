package com.lttrung.dormitory.database.data.local

import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UserLocal {
    fun login(): Single<LoginResponseBody>
    fun register(): Completable
    fun forgotPassword(): Completable
    fun verifyCode(): Completable
    fun resetPassword(): Completable
}