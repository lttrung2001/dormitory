package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface AdminLoginNetwork {
    fun login(username: String, password: String): Single<Unit>

    fun fetchStudentStats(): Single<List<StudentStat>>

    fun fetchRoomTypeStats(): Single<List<RoomTypeStat>>

    fun fetchGenderStats(): Single<GenderStats>
}