package com.lttrung.dormitory.domain.data.network

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface RoomNetwork {
    fun fetchRooms(roomTypeId: Int): Single<List<Room>>
    fun fetchRoomDetails(): Single<Type>
}