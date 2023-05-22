package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.network.AdminUserNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.repositories.AdminUserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminUserRepositoriesImpl @Inject constructor(
    override val network: AdminUserNetwork
) : AdminUserRepositories {
    override fun getStudentStats(): Single<List<StudentStat>> {
        return try {
            network.fetchStudentStats()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun getRoomTypeStats(): Single<List<RoomTypeStat>> {
        return try {
            network.fetchRoomTypeStats()
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun getGenderStats(): Single<GenderStats> {
        return try {
            network.fetchGenderStats()
        } catch (ex: Exception) {
            throw ex
        }
    }
}