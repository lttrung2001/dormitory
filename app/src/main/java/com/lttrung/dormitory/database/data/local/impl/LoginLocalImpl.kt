package com.lttrung.dormitory.database.data.local.impl

import android.content.SharedPreferences
import com.lttrung.dormitory.database.data.local.LoginLocal
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.utils.AppConstants.ACCESS_TOKEN
import javax.inject.Inject

class LoginLocalImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
    private val sharedPreferences: SharedPreferences
) : LoginLocal {
    override fun login(currentUser: CurrentUser) {
        currentUserDao.insertCurrentUser(currentUser)
        sharedPreferences.edit().putString(ACCESS_TOKEN, currentUser.token).apply()
    }

    override fun logout() {
        currentUserDao.deleteCurrentUser()
        sharedPreferences.edit().remove(ACCESS_TOKEN).apply()
    }

    override fun changePassword(newPassword: String) {
        val user = currentUserDao.currentUser
        user?.let {
            it.password = newPassword
            currentUserDao.updateCurrentUser(it)
        }
    }
}