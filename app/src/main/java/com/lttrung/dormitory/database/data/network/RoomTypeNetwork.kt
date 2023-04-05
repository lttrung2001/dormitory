package com.lttrung.dormitory.database.data.network

import com.lttrung.dormitory.database.data.network.models.RoomType
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RoomTypeNetwork {
    fun fetchRoomTypes(): Single<List<RoomType>>
}