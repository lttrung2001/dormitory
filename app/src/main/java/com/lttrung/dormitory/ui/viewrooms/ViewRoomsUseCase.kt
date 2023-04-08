package com.lttrung.dormitory.ui.viewrooms

import com.lttrung.dormitory.database.data.network.models.Room
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import javax.inject.Singleton

@Singleton
interface ViewRoomsUseCase {
    fun getRooms(roomTypeId: Int): Single<List<Room>>
}