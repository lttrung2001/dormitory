package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.RoomTypeStat
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface GetRoomTypeStatsUseCase {
    fun execute(): Single<List<RoomTypeStat>>
}