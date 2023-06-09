package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface AdminLoginRepositories {
    val network: AdminLoginNetwork
    fun login(username: String, password: String): Single<Unit>
    fun verifyAdmin(username: String, password: String, otp: String): Single<String>
}