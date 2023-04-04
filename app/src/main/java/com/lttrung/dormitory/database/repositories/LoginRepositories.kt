package com.lttrung.dormitory.database.repositories

import com.lttrung.dormitory.database.data.local.LoginLocal
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.LoginNetwork
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginRepositories {
    val local: LoginLocal
    val network: LoginNetwork
    fun login(username: String, password: String): Single<CurrentUser>
    fun forgotPassword(): Completable
    fun verifyCode(username: String, password: String, otp: String): Single<String>
    fun resetPassword(): Completable
    fun register(username: String, password: String): Single<String>
}