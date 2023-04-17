package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    fun fetchProfile(): Single<UserProfile>
    fun changePassword(oldPassword: String, newPassword: String): Single<Unit>
}