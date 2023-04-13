package com.lttrung.dormitory.ui.main.home

import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.repositories.RoomTypeRepositories
import com.lttrung.dormitory.database.repositories.UserRepositories
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val roomTypeRepositories: RoomTypeRepositories,
    private val userRepositories: UserRepositories
) : HomeUseCase {
    override fun getRoomTypes(): Single<List<RoomType>> {
        return roomTypeRepositories.fetchRoomTypes()
    }

    override fun getRoomContract(): Single<FetchRoomContractResponse> {
        return userRepositories.getRoomContract()
    }
}