package com.lttrung.dormitory.ui.registerroom

import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface RegisterRoomUseCase {
    fun registerRoom(roomId: Int): Single<Boolean>
}