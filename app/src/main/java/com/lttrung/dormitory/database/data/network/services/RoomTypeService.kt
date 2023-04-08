package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.RoomType
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface RoomTypeService {
    @GET("$PATH/room-type")
    fun fetchRoomTypes(): Single<Response<List<RoomType>>>

    companion object {
        private const val PATH = "api/student"
    }
}