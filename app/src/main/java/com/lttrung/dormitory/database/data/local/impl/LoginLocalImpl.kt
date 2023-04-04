package com.lttrung.dormitory.database.data.local.impl

import android.content.SharedPreferences
import com.lttrung.dormitory.database.data.local.LoginLocal
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.utils.AppConstants
import javax.inject.Inject

class LoginLocalImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
    private val sharedPreferences: SharedPreferences
) : LoginLocal {
    override fun login(currentUser: CurrentUser) {
        currentUserDao.insertCurrentUser(currentUser)
        sharedPreferences.edit().putString(AppConstants.ACCESS_TOKEN, currentUser.token).apply()
    }
}