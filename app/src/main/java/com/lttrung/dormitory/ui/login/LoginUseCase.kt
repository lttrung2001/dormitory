package com.lttrung.dormitory.ui.login

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginUseCase {
    fun login(username: String, password: String): Single<CurrentUser>
}