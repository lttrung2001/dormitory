package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomManagementRepositories
import com.lttrung.dormitory.domain.usecases.DeleteRoomManagementUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DeleteRoomManagementUseCaseImpl @Inject constructor(
    private val roomManagementRepositories: RoomManagementRepositories
) : DeleteRoomManagementUseCase {
    override fun execute(room: Room): Single<Room> {
        return roomManagementRepositories.deleteRoom(room)
    }
}