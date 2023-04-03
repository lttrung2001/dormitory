package com.lttrung.dormitory.database.repositories.login

import com.lttrung.dormitory.database.data.local.login.LoginLocal
import com.lttrung.dormitory.database.data.network.login.LoginNetwork
import io.reactivex.rxjava3.core.Completable
import javax.inject.Singleton

@Singleton
interface LoginRepositories {
    val local: LoginLocal
    val network: LoginNetwork
    abstract fun login(username: String, password: String): Completable
    abstract fun register(): Completable
    abstract fun forgotPassword(): Completable
    abstract fun verifyCode(): Completable
    abstract fun resetPassword(): Completable
}