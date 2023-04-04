package com.lttrung.dormitory.database.data.local

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import javax.inject.Singleton

@Singleton
interface LoginLocal {
    fun login(currentUser: CurrentUser)
}