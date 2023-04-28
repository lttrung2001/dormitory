package com.lttrung.dormitory.ui.roommanagement

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RoomsManagementUseCase {
    fun getRooms(roomTypeId: Int): Single<List<Room>>
    fun addRoom(room: Room): Single<Room>
    fun editRoom(room: Room): Single<Room>
    fun deleteRoom(room: Room): Single<Room>
}