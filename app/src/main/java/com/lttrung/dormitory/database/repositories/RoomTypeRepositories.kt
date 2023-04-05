package com.lttrung.dormitory.database.repositories

import com.lttrung.dormitory.database.data.network.RoomTypeNetwork
import com.lttrung.dormitory.database.data.network.models.RoomType
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RoomTypeRepositories {
    val network: RoomTypeNetwork
    fun fetchRoomTypes(): Single<List<RoomType>>
}