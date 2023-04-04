package com.lttrung.dormitory.database.data.local.login

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface LoginLocal {
    fun login(currentUser: CurrentUser)
}