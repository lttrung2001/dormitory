package com.lttrung.dormitory.database.data.local

import com.lttrung.dormitory.database.data.network.models.UserProfile
import javax.inject.Singleton

@Singleton
interface UserLocal {
    fun updateUserProfile(userProfile: UserProfile)
}