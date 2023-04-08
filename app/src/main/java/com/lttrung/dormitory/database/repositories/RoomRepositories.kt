package com.lttrung.dormitory.database.repositories

import com.lttrung.dormitory.database.data.network.RoomNetwork
import com.lttrung.dormitory.database.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface RoomRepositories {
    val network: RoomNetwork
    fun fetchRooms(roomTypeId: Int): Single<List<Room>>
    fun fetchRoomDetails(): Single<Type>
}