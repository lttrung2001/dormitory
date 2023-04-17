package com.lttrung.dormitory.domain.data.local

import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.data.network.models.UserProfile
import javax.inject.Singleton

@Singleton
interface UserLocal {
    fun updateUserProfile(userProfile: UserProfile)
    fun changePassword(newPassword: String)
    fun getCurrentUser(): CurrentUser?
    fun logout()
}