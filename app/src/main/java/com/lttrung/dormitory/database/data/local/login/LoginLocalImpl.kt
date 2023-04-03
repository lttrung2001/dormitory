package com.lttrung.dormitory.database.data.local.login

import android.content.SharedPreferences
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.login.LoginResponseBody
import com.lttrung.dormitory.utils.AppConstants
import io.reactivex.rxjava3.core.Completable
import java.io.IOException
import javax.inject.Inject

class LoginLocalImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
    private val sharedPreferences: SharedPreferences
) : LoginLocal {
    override fun login(body: LoginResponseBody): Completable {
        return try {
            val newUser = CurrentUser(body.studentId, body.role, body.token)
            currentUserDao.insertCurrentUser(newUser)
            sharedPreferences.edit().putString(AppConstants.ACCESS_TOKEN, body.token).apply()
            Completable.complete()
        } catch (ex: Exception) {
            Completable.error(IOException("Can not save login response to memory."))
        }
    }
}