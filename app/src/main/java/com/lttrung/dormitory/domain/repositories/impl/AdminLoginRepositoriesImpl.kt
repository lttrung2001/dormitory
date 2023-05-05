package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminLoginRepositoriesImpl @Inject constructor(
    override val network: AdminLoginNetwork
) : AdminLoginRepositories {
    override fun login(username: String, password: String): Single<Unit> {
        return try {
            network.login(username, password)
        } catch (ex: Exception) {
            throw ex
        }
    }

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