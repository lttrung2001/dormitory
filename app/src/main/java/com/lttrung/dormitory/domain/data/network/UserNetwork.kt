package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    fun fetchProfile(): Single<StudentProfile>
    fun changePassword(oldPassword: String, newPassword: String): Single<Unit>
}