package com.lttrung.dormitory.ui.login

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginUseCase {
    fun login(username: String, password: String): Single<CurrentUser>
}