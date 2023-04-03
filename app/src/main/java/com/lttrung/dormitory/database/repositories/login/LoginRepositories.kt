package com.lttrung.dormitory.database.repositories.login

import com.lttrung.dormitory.database.data.local.login.LoginLocal
import com.lttrung.dormitory.database.data.network.login.LoginNetwork
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginRepositories {
    val local: LoginLocal
    val network: LoginNetwork
    fun login(username: String, password: String): Single<LoginResponseBody>
    fun forgotPassword(): Completable
    fun verifyCode(): Completable
    fun resetPassword(): Completable
    fun register(username: String, password: String): Single<String>
}