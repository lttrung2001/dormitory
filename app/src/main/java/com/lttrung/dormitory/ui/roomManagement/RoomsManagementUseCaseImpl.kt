package com.lttrung.dormitory.ui.roomManagement

import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.database.repositories.RoomManagementRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomsManagementUseCaseImpl @Inject constructor(
    private val roomManagementRepositories: RoomManagementRepositories
) : RoomsManagementUseCase {
    override fun getRooms(roomTypeId: Int): Single<List<Room>> {
        return roomManagementRepositories.fetchRooms(roomTypeId)
    }

    override fun addRoom(room: Room): Single<Room> {
        return roomManagementRepositories.addRoom(room)

    }

    override fun editRoom(room: Room): Single<Room> {
        return roomManagementRepositories.editRoom(room)
    }

    override fun deleteRoom(room: Room): Single<Room> {
        return roomManagementRepositories.deleteRoom(room)
    }
}