package com.lttrung.dormitory.domain.data.network.impl

import com.lttrung.dormitory.domain.data.network.AdminUserNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.data.network.services.AdminUserService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.exceptions.NotFoundException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminUserRetrofitImpl @Inject constructor(
    private val service: AdminUserService
) : AdminUserNetwork {
    override fun fetchStudentStats(): Single<List<StudentStat>> {
        return service.fetchStudentStats().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> {
                    response.body()!!
                }
                else -> {
                    throw FailedException()
                }
            }
        }
    }

    override fun fetchRoomTypeStats(): Single<List<RoomTypeStat>> {
        return service.fetchRoomTypeStats().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> {
                    response.body()!!
                }
                else -> {
                    throw FailedException()
                }
            }
        }
    }

    override fun fetchGenderStats(): Single<GenderStats> {
        return service.fetchGenderStats().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> {
                    response.body()?.let { stats ->
                        if (stats.isEmpty()) {
                            throw NotFoundException()
                        } else {
                            stats[0]
                        }
                    }
                }
                else -> {
                    throw FailedException()
                }
            }!!
        }
    }
}