package com.lttrung.dormitory.ui.main.home

import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.repositories.RoomTypeRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val roomTypeRepositories: RoomTypeRepositories
) : HomeUseCase {
    override fun getRoomTypes(): Single<List<RoomType>> {
        return roomTypeRepositories.fetchRoomTypes()
    }
}