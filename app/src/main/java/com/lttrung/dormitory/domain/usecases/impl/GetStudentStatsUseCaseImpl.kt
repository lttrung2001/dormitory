package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.StudentStat
import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.domain.repositories.AdminUserRepositories
import com.lttrung.dormitory.domain.usecases.GetStudentStatsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetStudentStatsUseCaseImpl @Inject constructor(
    private val adminUserRepositories: AdminUserRepositories
) : GetStudentStatsUseCase {
    override fun execute(): Single<List<StudentStat>> {
        return adminUserRepositories.getStudentStats()
    }
}