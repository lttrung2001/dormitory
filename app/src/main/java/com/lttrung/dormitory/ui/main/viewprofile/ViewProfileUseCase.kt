package com.lttrung.dormitory.ui.main.viewprofile

import com.lttrung.dormitory.database.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewProfileUseCase {
    fun getUserProfile(): Single<UserProfile>
}