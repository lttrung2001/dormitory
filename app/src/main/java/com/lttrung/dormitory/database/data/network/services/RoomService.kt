package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.database.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomService {
    @GET("$PATH/{idLoaiPhong}")
    fun fetchRooms(@Path("idLoaiPhong") roomTypeId: Int): Single<Response<List<Room>>>

    companion object {
        private const val PATH = "api/student/room-details/5/room"
    }
}