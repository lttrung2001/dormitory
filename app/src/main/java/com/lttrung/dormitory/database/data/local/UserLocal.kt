package com.lttrung.dormitory.database.data.local

import com.lttrung.dormitory.database.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.database.data.network.models.UserProfile
import javax.inject.Singleton

@Singleton
interface UserLocal {
    fun updateUserProfile(userProfile: UserProfile)
    fun changePassword(newPassword: String)
    fun getCurrentUser(): CurrentUser
}