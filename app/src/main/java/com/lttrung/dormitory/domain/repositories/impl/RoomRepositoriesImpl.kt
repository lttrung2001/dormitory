package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.network.RoomNetwork
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomRepositories
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