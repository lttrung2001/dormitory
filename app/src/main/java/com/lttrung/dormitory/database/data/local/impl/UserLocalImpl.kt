package com.lttrung.dormitory.database.data.local.impl

import com.lttrung.dormitory.database.data.local.UserLocal
import com.lttrung.dormitory.database.data.local.room.dao.CurrentUserDao
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.local.room.entities.CurrentUserProfile
import com.lttrung.dormitory.database.data.network.models.UserProfile
import javax.inject.Inject

class UserLocalImpl @Inject constructor(
    private val currentUserDao: CurrentUserDao,
) : UserLocal {
    override fun updateUserProfile(userProfile: UserProfile) {
        val currentUser = currentUserDao.currentUser.blockingGet()
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

    override fun changePassword(newPassword: String) {
        val currentUser = currentUserDao.currentUser.blockingGet()
        currentUser.password = newPassword
        currentUserDao.updateCurrentUser(currentUser)
    }

    override fun getCurrentUser(): CurrentUser {
        return currentUserDao.currentUser.blockingGet()
    }
}