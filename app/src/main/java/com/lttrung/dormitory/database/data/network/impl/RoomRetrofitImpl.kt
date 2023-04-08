package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.RoomNetwork
import com.lttrung.dormitory.database.data.network.models.Room
import com.lttrung.dormitory.database.data.network.services.RoomService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Inject

class RoomRetrofitImpl @Inject constructor(
    private val service: RoomService
) : RoomNetwork {
    override fun fetchRooms(roomTypeId: Int): Single<List<Room>> {
        return service.fetchRooms(roomTypeId).map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> response.body()!!
                else -> throw FailedException(response.message())
            }
        }
    }

    override fun fetchRoomDetails(): Single<Type> {
        TODO("Not yet implemented")
    }
}