package com.lttrung.dormitory.ui.viewrooms

import com.lttrung.dormitory.database.data.network.models.Room
import com.lttrung.dormitory.database.repositories.RoomRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ViewRoomsUseCaseImpl @Inject constructor(
    private val roomRepositories: RoomRepositories
) : ViewRoomsUseCase {
    override fun getRooms(roomTypeId: Int): Single<List<Room>> {
        return roomRepositories.fetchRooms(roomTypeId)
    }
}