package com.lttrung.dormitory.ui.main.home

import com.lttrung.dormitory.database.data.network.models.RoomType
import com.lttrung.dormitory.database.data.network.models.UserProfileResponse
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface HomeUseCase {
    fun getRoomTypes(): Single<List<RoomType>>
    fun getUserProfile(): Single<UserProfileResponse>
}