package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomRepositories
import com.lttrung.dormitory.domain.usecases.ViewRoomsUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewRoomsUseCaseImpl @Inject constructor(
    private val roomRepositories: RoomRepositories
) : ViewRoomsUseCase {
    override fun execute(roomTypeId: Int): Single<List<Room>> {
        return roomRepositories.fetchRooms(roomTypeId)
    }
}