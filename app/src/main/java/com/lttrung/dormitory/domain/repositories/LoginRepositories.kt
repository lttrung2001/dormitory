package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.local.LoginLocal
import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.data.network.LoginNetwork
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginRepositories {
    val local: LoginLocal
    val network: LoginNetwork
    fun login(username: String, password: String): Single<CurrentUser>
    fun forgotPassword(username: String): Single<Unit>
    fun verifyCode(username: String, password: String, otp: String): Single<Unit>
    fun register(username: String, password: String): Single<Unit>
}