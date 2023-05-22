package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.local.LoginLocal
import com.lttrung.dormitory.domain.data.local.room.entities.CurrentUser
import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AdminLoginRepositoriesImpl @Inject constructor(
    override val network: AdminLoginNetwork,
    private val loginLocal: LoginLocal
) : AdminLoginRepositories {
    override fun login(username: String, password: String): Single<Unit> {
        return try {
            network.login(username, password)
        } catch (ex: Exception) {
            throw ex
        }
    }

    override fun verifyAdmin(username: String, password: String, otp: String): Single<String> {
        return try {
            network.verifyAdmin(username, password, otp).map {
                loginLocal.login(CurrentUser(it.studentId, "", it.roles, it.token))
                it.token
            }
        } catch (ex: Exception) {
            throw ex
        }
    }
}