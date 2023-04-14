package com.lttrung.dormitory.database.data.network

import com.lttrung.dormitory.database.data.network.models.FetchRoomContractResponse
import com.lttrung.dormitory.database.data.network.models.UserProfile
import io.reactivex.rxjava3.core.Single
import javax.inject.Singleton

@Singleton
interface UserNetwork {
    fun fetchProfile(): Single<UserProfile>
    fun changePassword(oldPassword: String, newPassword: String): Single<Boolean>
    fun getRoomContract(): Single<FetchRoomContractResponse>
    fun registerRoom(roomId: Int): Single<Boolean>
}