package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.network.AdminLoginNetwork
import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.exceptions.FailedException
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

    override fun getStudentStats(termId: Int): Single<List<StudentStat>> {
        return try {
            network.fetchStudentStats(termId)
        } catch (ex: Exception) {
            throw ex
        }
    }
}