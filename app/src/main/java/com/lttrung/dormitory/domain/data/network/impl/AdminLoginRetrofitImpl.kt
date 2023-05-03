package com.lttrung.dormitory.domain.data.network.impl

import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.data.network.services.AdminLoginService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminLoginRetrofitImpl @Inject constructor(
    private val service: AdminLoginService
) : AdminLoginNetwork {
    override fun login(username: String, password: String): Single<Unit> {
        val body = hashMapOf<String, String>()
        body["username"] = username
        body["password"] = password
        return service.login(body).map { response ->
            when(response.code()) {
                HttpStatusCodes.OK -> {
                    response.body()!!
                } else -> {
                    throw FailedException()
                }
            }
        }
    }

    override fun fetchStudentStats(term: Int): Single<List<StudentStat>> {
        return service.fetchStudentStats(term).map { response ->
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
}