package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import com.lttrung.dormitory.domain.repositories.AdminLoginRepositories
import com.lttrung.dormitory.domain.repositories.AdminUserRepositories
import com.lttrung.dormitory.domain.usecases.GetRoomTypeStatsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRoomTypeStatsUseCaseImpl @Inject constructor(
    private val adminUserRepositories: AdminUserRepositories
) : GetRoomTypeStatsUseCase {
    override fun execute(): Single<List<RoomTypeStat>> {
        return adminUserRepositories.getRoomTypeStats()
    }
}