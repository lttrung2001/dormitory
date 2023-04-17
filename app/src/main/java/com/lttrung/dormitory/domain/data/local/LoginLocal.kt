package com.lttrung.dormitory.domain.data.local

import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import javax.inject.Singleton

@Singleton
interface LoginLocal {
    fun login(currentUser: CurrentUser)
    fun logout()
    fun changePassword(newPassword: String)
}