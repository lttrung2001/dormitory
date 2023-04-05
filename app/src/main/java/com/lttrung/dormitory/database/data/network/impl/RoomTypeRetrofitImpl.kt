package com.lttrung.dormitory.database.data.network.impl

import com.lttrung.dormitory.database.data.network.RoomTypeNetwork
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.data.network.services.RoomTypeService
import com.lttrung.dormitory.exceptions.FailedException
import com.lttrung.dormitory.utils.HttpStatusCodes
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomTypeRetrofitImpl @Inject constructor(
    private val service: RoomTypeService
) : RoomTypeNetwork {
    override fun fetchRoomTypes(): Single<List<RoomType>> {
        return service.fetchRoomTypes().map { response ->
            when (response.code()) {
                HttpStatusCodes.OK -> { response.body()!! }
                else -> { throw FailedException() }
            }
        }
    }
}