package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.local.UserLocal
import com.lttrung.dormitory.domain.data.network.UserNetwork
import com.lttrung.dormitory.domain.data.network.models.StudentProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UserRepositories {
    val local: UserLocal
    val network: UserNetwork
    fun fetchProfile(): Single<StudentProfile>
    fun changePassword(oldPassword: String, newPassword: String): Single<Unit>
    fun logout()
}