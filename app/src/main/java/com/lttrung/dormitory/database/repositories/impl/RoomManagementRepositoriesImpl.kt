package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.network.RoomManagementNetwork
import com.lttrung.dormitory.database.repositories.RoomManagementRepositories
import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Inject

class RoomManagementRepositoriesImpl @Inject constructor(override val network: RoomManagementNetwork) :
    RoomManagementRepositories {
    override fun fetchRooms(roomTypeId: Int): Single<List<Room>> {
        return network.fetchRooms(roomTypeId)
    }

    override fun addRoom(room: Room): Single<Room> {
        return network.addRoom(room)
    }

    override fun editRoom(room: Room): Single<Room> {
        return network.editRoom(room)
    }

    override fun deleteRoom(room: Room): Single<Room> {
        return network.deleteRoom(room)
    }
}