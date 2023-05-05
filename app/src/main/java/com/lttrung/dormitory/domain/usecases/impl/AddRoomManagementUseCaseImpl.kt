package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.Room
import com.lttrung.dormitory.domain.repositories.RoomManagementRepositories
import com.lttrung.dormitory.domain.usecases.AddRoomManagementUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class AddRoomManagementUseCaseImpl @Inject constructor(
    private val roomManagementRepositories: RoomManagementRepositories
) : AddRoomManagementUseCase {
    override fun execute(room: Room): Single<Room> {
        return roomManagementRepositories.addRoom(room)
    }
    // how about delete and update room?

}