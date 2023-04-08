package com.lttrung.dormitory.database.repositories.impl

import com.lttrung.dormitory.database.data.network.RoomNetwork
import com.lttrung.dormitory.database.data.network.models.Room
import com.lttrung.dormitory.database.repositories.RoomRepositories
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Inject

class RoomRepositoriesImpl @Inject constructor(override val network: RoomNetwork) :
    RoomRepositories {
    override fun fetchRooms(roomTypeId: Int): Single<List<Room>> {
        return network.fetchRooms(roomTypeId)
    }

    override fun fetchRoomDetails(): Single<Type> {
        TODO("Not yet implemented")
    }
}