package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.RoomManagementNetwork
import com.lttrung.dormitory.database.data.network.services.RoomManagementService
import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomManagementRetrofitImpl @Inject constructor(
    private val service: RoomManagementService
) : RoomManagementNetwork {
    override fun fetchRooms(roomTypeId: Int): Single<List<Room>> {
        return service.fetchRooms(roomTypeId).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.message())
            }
        }
    }

    override fun addRoom(room: Room): Single<Room> {
        return service.addRoom(room).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.message())
            }
        }
    }

    override fun editRoom(room: Room): Single<Room> {
        return service.editRoom(room).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.message())
            }
        }
    }

    override fun deleteRoom(room: Room): Single<Room> {
        return service.deleteRoom(room).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.message())
            }
        }
//        TODO("Not yet implemented")
    }
}