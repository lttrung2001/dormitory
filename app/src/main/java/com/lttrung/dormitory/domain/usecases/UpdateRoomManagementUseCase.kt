package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UpdateRoomManagementUseCase {
    fun execute(room: Room): Single<Room>
}