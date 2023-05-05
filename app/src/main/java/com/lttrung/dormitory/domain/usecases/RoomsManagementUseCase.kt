package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RoomsManagementUseCase {
    fun execute(roomTypeId: Int): Single<List<Room>>
}