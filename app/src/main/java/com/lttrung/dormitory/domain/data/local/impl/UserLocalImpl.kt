package com.lttrung.dormitory.domain.data.local.impl

import android.content.SharedPreferences
import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUserProfile
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import com.lttrung.dormitory.utils.AppConstants.ACCESS_TOKEN
import javax.inject.Inject

class UserLocalImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
    private val sharedPreferences: SharedPreferences
) : UserLocal {
    override fun updateUserProfile(userProfile: StudentProfile) {
        val currentUser = currentUserDao.currentUser
        currentUser?.let {
            val currentUserProfile = CurrentUserProfile(
                userProfile.fullName,
                userProfile.isMale,
                userProfile.dob.time,
                userProfile.email,
                userProfile.identityCardId,
                userProfile.phoneNumber
            )
            currentUser.profile = currentUserProfile
            currentUserDao.updateCurrentUser(currentUser)
        }
    }

    override fun changePassword(newPassword: String) {
        val currentUser = currentUserDao.currentUser
        currentUser?.let {
            it.password = newPassword
            currentUserDao.updateCurrentUser(it)
        }
    }

    override fun getCurrentUser(): CurrentUser? {
        return currentUserDao.currentUser
    }

    override fun logout() {
        currentUserDao.deleteCurrentUser()
        sharedPreferences.edit().remove(ACCESS_TOKEN).apply()
    }
}