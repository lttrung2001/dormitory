package com.lttrung.dormitory.domain.usecases

import com.lttrung.dormitory.domain.data.local.models.FilterSortModel
import com.lttrung.dormitory.domain.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface ViewRoomsUseCase {
    fun execute(roomTypeId: Int, filterSortModel: FilterSortModel?): Single<List<Room>>
}