package com.lttrung.dormitory.domain.repositories

import com.lttrung.dormitory.domain.data.network.AdminUserNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface AdminUserRepositories {
    val network: AdminUserNetwork
    fun getStudentStats(): Single<List<StudentStat>>
    fun getRoomTypeStats(): Single<List<RoomTypeStat>>
    fun getGenderStats(): Single<GenderStats>
}