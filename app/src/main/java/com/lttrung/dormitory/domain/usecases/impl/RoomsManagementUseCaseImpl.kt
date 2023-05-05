package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomManagementRepositories
import com.lttrung.dormitory.domain.usecases.RoomsManagementUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomsManagementUseCaseImpl @Inject constructor(
    private val roomManagementRepositories: RoomManagementRepositories
) : RoomsManagementUseCase {
//    override fun getRooms(roomTypeId: Int): Single<List<Room>> {
//    }
//
//    override fun addRoom(room: Room): Single<Room> {
//
//    }
//
//    override fun editRoom(room: Room): Single<Room> {
//        return roomManagementRepositories.editRoom(room)
//    }
//
//    override fun deleteRoom(room: Room): Single<Room> {
//        return roomManagementRepositories.deleteRoom(room)
//    }

    override fun execute(roomTypeId: Int): Single<List<Room>> {
        return roomManagementRepositories.fetchRooms(roomTypeId)
    }
}