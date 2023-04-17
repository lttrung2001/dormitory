package com.lttrung.dormitory.domain.usecases.impl

import com.lttrung.dormitory.domain.data.network.models.RoomType
import com.lttrung.dormitory.domain.repositories.RoomTypeRepositories
import com.lttrung.dormitory.domain.usecases.GetRoomTypesUseCase
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetRoomTypesUseCaseImpl @Inject constructor(
    private val roomTypeRepositories: RoomTypeRepositories
) : GetRoomTypesUseCase {
    override fun execute(): Single<List<RoomType>> {
        return roomTypeRepositories.fetchRoomTypes()
    }
}