package com.lttrung.dormitory.database.data.network.services

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.*

interface RoomManagementService {
    @GET("$PATH/{idLoaiPhong}")
    fun fetchRooms(@Path("idLoaiPhong") roomTypeId: Int): Single<Response<List<Room>>>

    @FormUrlEncoded
    @POST("$PATH/{room}")
    fun addRoom(@Field("room") room: Room): Single<Response<Room>>

    @FormUrlEncoded
    @PUT("$PATH/{room}")
    fun editRoom(@Field("room") room: Room): Single<Response<Room>>

    @FormUrlEncoded
    @DELETE("$PATH/{room}")
    fun deleteRoom(@Field("room") room: Room): Single<Response<Room>>

    companion object {
        private const val PATH = "api/student/room-details/5/room"
    }
}