package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.RoomType
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface GetRoomTypesUseCase {
    fun execute(): Single<List<RoomType>>
}