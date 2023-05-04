package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface RoomManagementNetwork {
    fun fetchRooms(roomTypeId: Int): Single<List<Room>>
    fun addRoom(room: Room): Single<Room>
    fun editRoom(room: Room): Single<Room>
    fun deleteRoom(room: Room): Single<Room>
}