package com.lttrung.dormitory.domain.repositories.impl

import com.lttrung.dormitory.domain.data.network.RoomTypeNetwork
import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.domain.repositories.RoomTypeRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RoomTypeRepositoriesImpl @Inject constructor(override val network: RoomTypeNetwork) :
    RoomTypeRepositories {
    override fun fetchRoomTypes(): Single<List<RoomType>> {
        return network.fetchRoomTypes()
    }
}