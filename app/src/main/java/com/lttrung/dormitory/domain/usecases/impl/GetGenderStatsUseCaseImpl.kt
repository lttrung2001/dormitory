package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.GenderStats
import com.lttrung.dormitory.domain.repositories.AdminUserRepositories
import com.lttrung.dormitory.domain.usecases.GetGenderStatsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGenderStatsUseCaseImpl @Inject constructor(
    private val adminUserRepositories: AdminUserRepositories
) : GetGenderStatsUseCase {
    override fun execute(): Single<GenderStats> {
        return adminUserRepositories.getGenderStats()
    }
}